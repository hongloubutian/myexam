package cn.jhc.myexam.vaadin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.eobjects.metamodel.DataContext;
import org.eobjects.metamodel.excel.ExcelConfiguration;
import org.eobjects.metamodel.excel.ExcelDataContext;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public abstract class ExcelFileUploadHandler implements Receiver,
	StartedListener, SucceededListener {
	
	private static File UPLOAD_FOLDER = new File("/tmp/myexamuploads/");
	
	//TODO: 上传的临时文件不会自动删除，会不会造成服务器负担？
	private File file = null; 
	
	private static final long FILE_SIZE_LIMIT = 5*1024*1024; //5MB
	
	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream outputStream = null;
		try {
			//上传文件的目录必须先创建好，否则会出异常。
			if( ! UPLOAD_FOLDER.exists() )
				UPLOAD_FOLDER.mkdir();
			file = new File(UPLOAD_FOLDER.getAbsolutePath() + "/" + filename
					+ "." + System.currentTimeMillis());
			outputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			Notification.show("服务器无法接收文件，请联系管理员。<br/>", e.getMessage(), Type.ERROR_MESSAGE);
			return null;
		}
		return outputStream;
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		ExcelConfiguration configuration = new ExcelConfiguration(1, true, true);
		DataContext context = new ExcelDataContext(file, configuration);
		if( !validateColumnNames(context) ) {
			Notification.show("表结构不合要求，请修改后重新上传！", Type.ERROR_MESSAGE);
			return;
		}
		showDataWindow(context);
	}

	protected abstract void showDataWindow(DataContext context);

	protected abstract boolean validateColumnNames(DataContext context);
	
	@Override
	public void uploadStarted(StartedEvent event) {
		Upload upload = event.getUpload();
		if(event.getContentLength() > FILE_SIZE_LIMIT) {
			upload.interruptUpload();
			Notification.show("上传的Excel文件不能超过5MB！", Notification.Type.ERROR_MESSAGE);
			return;
		}
		if(	 ! event.getFilename().endsWith(".xls") 
						&& ! event.getFilename().endsWith(".xlsx") ) {
			upload.interruptUpload();
			Notification.show("只能上传后缀为.xls或.xlsx的文件！", Notification.Type.ERROR_MESSAGE);
		}
	}

}
