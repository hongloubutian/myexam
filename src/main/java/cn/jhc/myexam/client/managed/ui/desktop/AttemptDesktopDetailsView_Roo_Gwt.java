// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package cn.jhc.myexam.client.managed.ui.desktop;
import cn.jhc.myexam.client.managed.ui.AttemptDetailsView;
import cn.jhc.myexam.client.proxy.AttemptProxy;
import cn.jhc.myexam.client.proxy.QuizProxy;
import cn.jhc.myexam.client.proxy.UserProxy;
import cn.jhc.myexam.client.scaffold.place.ProxyListView;
import cn.jhc.myexam.shared.domain.QuestionType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AttemptDesktopDetailsView_Roo_Gwt extends Composite implements AttemptDetailsView {

    @UiField
    SpanElement id;

    @UiField
    SpanElement questionType;

    @UiField
    SpanElement answer;

    @UiField
    SpanElement submitDate;

    @UiField
    SpanElement user;

    @UiField
    SpanElement quiz;

    @UiField
    SpanElement questionId;

    @UiField
    SpanElement version;

    AttemptProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(AttemptProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        questionType.setInnerText(proxy.getQuestionType() == null ? "" : String.valueOf(proxy.getQuestionType()));
        answer.setInnerText(proxy.getAnswer() == null ? "" : String.valueOf(proxy.getAnswer()));
        submitDate.setInnerText(proxy.getSubmitDate() == null ? "" : DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(proxy.getSubmitDate()));
        user.setInnerText(proxy.getUser() == null ? "" : cn.jhc.myexam.client.managed.ui.renderer.UserProxyRenderer.instance().render(proxy.getUser()));
        quiz.setInnerText(proxy.getQuiz() == null ? "" : cn.jhc.myexam.client.managed.ui.renderer.QuizProxyRenderer.instance().render(proxy.getQuiz()));
        questionId.setInnerText(proxy.getQuestionId() == null ? "" : String.valueOf(proxy.getQuestionId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        displayRenderer.setInnerText(cn.jhc.myexam.client.managed.ui.renderer.AttemptProxyRenderer.instance().render(proxy));
    }
}
