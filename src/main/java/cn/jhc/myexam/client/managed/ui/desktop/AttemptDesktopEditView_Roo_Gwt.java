// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package cn.jhc.myexam.client.managed.ui.desktop;
import cn.jhc.myexam.client.managed.activity.AttemptEditActivityWrapper;
import cn.jhc.myexam.client.managed.activity.AttemptEditActivityWrapper.View;
import cn.jhc.myexam.client.managed.ui.AttemptEditView;
import cn.jhc.myexam.client.proxy.AttemptProxy;
import cn.jhc.myexam.client.proxy.QuizProxy;
import cn.jhc.myexam.client.proxy.UserProxy;
import cn.jhc.myexam.shared.domain.QuestionType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import java.util.Collection;
import java.util.List;

public abstract class AttemptDesktopEditView_Roo_Gwt extends Composite implements View<AttemptDesktopEditView> {

    @UiField(provided = true)
    ValueListBox<QuestionType> questionType = new ValueListBox<QuestionType>(new AbstractRenderer<cn.jhc.myexam.shared.domain.QuestionType>() {

        public String render(cn.jhc.myexam.shared.domain.QuestionType obj) {
            return obj == null ? "" : String.valueOf(obj);
        }
    });

    @UiField
    TextBox answer;

    @UiField
    DateBox submitDate;

    @UiField(provided = true)
    ValueListBox<UserProxy> user = new ValueListBox<UserProxy>(cn.jhc.myexam.client.managed.ui.renderer.UserProxyRenderer.instance(), new com.google.web.bindery.requestfactory.gwt.ui.client.EntityProxyKeyProvider<cn.jhc.myexam.client.proxy.UserProxy>());

    @UiField(provided = true)
    ValueListBox<QuizProxy> quiz = new ValueListBox<QuizProxy>(cn.jhc.myexam.client.managed.ui.renderer.QuizProxyRenderer.instance(), new com.google.web.bindery.requestfactory.gwt.ui.client.EntityProxyKeyProvider<cn.jhc.myexam.client.proxy.QuizProxy>());

    @UiField
    LongBox questionId;

    public void setUserPickerValues(Collection<UserProxy> values) {
        user.setAcceptableValues(values);
    }

    public void setQuestionTypePickerValues(Collection<QuestionType> values) {
        questionType.setAcceptableValues(values);
    }

    public void setQuizPickerValues(Collection<QuizProxy> values) {
        quiz.setAcceptableValues(values);
    }
}
