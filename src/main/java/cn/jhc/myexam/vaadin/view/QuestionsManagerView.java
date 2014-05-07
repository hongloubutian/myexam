package cn.jhc.myexam.vaadin.view;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.jhc.myexam.server.domain.Category;
import cn.jhc.myexam.server.service.CategoryService;
import cn.jhc.myexam.server.service.QuestionsService;
import cn.jhc.myexam.shared.domain.QuestionType;
import cn.jhc.myexam.vaadin.builder.VaadinEntityBuilder;
import cn.jhc.myexam.vaadin.builder.VaadinEntityBuilder.EntityFormCallback;
import cn.jhc.myexam.vaadin.factory.QuestionTypeFactory;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@Component @Scope("prototype") @SuppressWarnings("serial")
public class QuestionsManagerView extends CustomComponent implements View, ValueChangeListener{

	@Autowired
	private transient QuestionsService questionsService;
	
	@Autowired
	private transient CategoryService categoryService;

	private VerticalLayout mainLayout;

	private Table questionsTable;

	private VerticalLayout categoryLayout;
	
	private Tree categoryTree;
	
	private HorizontalLayout selectionsLayout;
	
	private ComboBox questionTypesComboBox;

	private HorizontalLayout buttonsLayout;

	private NativeButton addQuestionButton;

	private NativeButton importQuestionsButton;

	private BeanItemContainer<?> container;

	private Label currentCategoryLabel;

	/**
	 * The constructor should first build the MAIN layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */

	public QuestionsManagerView() {

		buildMainLayout();
		setCompositionRoot(mainLayout);

//		questionTypesComboBox.addValueChangeListener(this);
//		questionTypesComboBox.setValue(QuestionType.SINGLE_CHOICE);
//		
//		addQuestionButton.addClickListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				QuestionType type = (QuestionType) questionTypesComboBox.getValue();
//				if(type == null) {
//					Notification.show("没有选择题目类型！", Notification.Type.ERROR_MESSAGE);
//					return;
//				}
//				UI.getCurrent().addWindow(new AddQuestionWindow(QuestionsManagerView.this, type, questionsService));
//			}
//		});
	}

	private VerticalLayout buildMainLayout() {
		setSizeFull();
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		buildCategoryTreeLayout();
		mainLayout.addComponent(categoryLayout);
		mainLayout.setComponentAlignment(categoryLayout, Alignment.TOP_CENTER);
		mainLayout.setExpandRatio(categoryLayout, 1);
		
		FormLayout addCategoryLayout = buildAddCategoryLayout();
		addCategoryLayout.setWidth("100%");
		mainLayout.addComponent(addCategoryLayout);
		mainLayout.setComponentAlignment(addCategoryLayout, Alignment.MIDDLE_CENTER);
		
//		// selectionsLayout
//		buildSelectionsLayout();
//		mainLayout.addComponent(selectionsLayout);
//		
//		// questionsTable
//		questionsTable = new Table();
//		questionsTable.setImmediate(true);
//		questionsTable.setWidth("100.0%");
//		questionsTable.setHeight("-1px");
//		mainLayout.addComponent(questionsTable);
//		
//		buildButtonsLayout();
//		mainLayout.addComponent(buttonsLayout);
		
		return mainLayout;
	}
	
	private void buildCategoryTreeLayout() {
		categoryLayout = new VerticalLayout();
		categoryLayout.setWidth("100%");
		
		categoryTree = new Tree("题库类别");
		categoryLayout.addComponent(categoryTree);
		categoryLayout.setComponentAlignment(categoryTree, Alignment.MIDDLE_CENTER);
		
	}

	private FormLayout buildAddCategoryLayout() {
		FormLayout formLayout = VaadinEntityBuilder.create(Category.class)
				.buildFormLayout("添加新类别", new EntityFormCallback<Category>() {

					@Override
					public void onSave(Category item) {
					}

					@Override
					public void addCustomField(FormLayout formLayout,
							FieldGroup fieldGroup) {
						ComboBox box = new ComboBox("选择父类别",Arrays.asList("类别一","类别二"));
						fieldGroup.bind(box, "parent");
						formLayout.addComponent(box);
					}
				});
		return formLayout;
	}
	
	private HorizontalLayout buildSelectionsLayout() {
		// common part: create layout
		selectionsLayout = new HorizontalLayout();
		selectionsLayout.setImmediate(false);
		selectionsLayout.setWidth("100.0%");
		selectionsLayout.setHeight("-1px");
		selectionsLayout.setMargin(false);
		
		// questionTypesComboBox
		final Container questionTypeContainer = QuestionTypeFactory.getQuestionTypeContainer();
		questionTypesComboBox = new ComboBox("选择题型", questionTypeContainer);
		questionTypesComboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		questionTypesComboBox.setItemCaptionPropertyId("description");
		questionTypesComboBox.setImmediate(true);
		questionTypesComboBox.setWidth("-1px");
		questionTypesComboBox.setHeight("-1px");
		selectionsLayout.addComponent(questionTypesComboBox);

		return selectionsLayout;
	}

	private HorizontalLayout buildButtonsLayout() {
		// common part: create layout
		buttonsLayout = new HorizontalLayout();
		buttonsLayout.setImmediate(false);
		buttonsLayout.setWidth("-1px");
		buttonsLayout.setHeight("40px");
		buttonsLayout.setMargin(false);
		buttonsLayout.setSpacing(true);
		
		// addQuestionButton
		addQuestionButton = new NativeButton();
		addQuestionButton.setStyleName("page-button");
		addQuestionButton.setCaption("添加题目");
		addQuestionButton.setImmediate(true);
		addQuestionButton.setDescription("点击这里可以添加单个题目");
		addQuestionButton.setWidth("-1px");
		addQuestionButton.setHeight("100.0%");
		buttonsLayout.addComponent(addQuestionButton);
		buttonsLayout
				.setComponentAlignment(addQuestionButton, new Alignment(48));
		
		// importQuestionsButton
		importQuestionsButton = new NativeButton();
		importQuestionsButton.setStyleName("page-button");
		importQuestionsButton.setCaption("从Excel导入多个题目");
		importQuestionsButton.setImmediate(true);
		importQuestionsButton.setDescription("点击这里可以从Excel中导入多个题目");
		importQuestionsButton.setWidth("-1px");
		importQuestionsButton.setHeight("100.0%");
		buttonsLayout.addComponent(importQuestionsButton);
		buttonsLayout.setComponentAlignment(importQuestionsButton,
				new Alignment(48));
		
		return buttonsLayout;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void valueChange(ValueChangeEvent event) {
		QuestionType type = (QuestionType)event.getProperty().getValue();
		List list = questionsService.findAllQuestions(type);
		Class<?> theClass = type.getEntityClass();
		container = new BeanItemContainer(theClass);
		container.addAll(list);
		VaadinEntityBuilder.create(theClass).modifyTable(questionsTable, container);
	}

	public BeanItemContainer<?> getTableContainer() {
		return container;
	}

}
