// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package cn.jhc.myexam.client.managed.ui.renderer;
import cn.jhc.myexam.client.managed.request.ApplicationEntityTypesProcessor;
import cn.jhc.myexam.client.proxy.AttemptProxy;
import cn.jhc.myexam.client.proxy.BriefAnswerProxy;
import cn.jhc.myexam.client.proxy.CategoryProxy;
import cn.jhc.myexam.client.proxy.FillBlankProxy;
import cn.jhc.myexam.client.proxy.GlossaryProxy;
import cn.jhc.myexam.client.proxy.QuizProxy;
import cn.jhc.myexam.client.proxy.RoleProxy;
import cn.jhc.myexam.client.proxy.SingleChoiceProxy;
import cn.jhc.myexam.client.proxy.TrueOrFalseProxy;
import cn.jhc.myexam.client.proxy.UserProxy;
import cn.jhc.myexam.client.scaffold.place.ProxyListPlace;
import com.google.gwt.text.shared.AbstractRenderer;

public abstract class ApplicationListPlaceRenderer_Roo_Gwt extends AbstractRenderer<ProxyListPlace> {

    public String render(ProxyListPlace object) {
        return new ApplicationEntityTypesProcessor<String>() {

            @Override
            public void handleAttempt(AttemptProxy isNull) {
                setResult("Attempts");
            }

            @Override
            public void handleBriefAnswer(BriefAnswerProxy isNull) {
                setResult("BriefAnswers");
            }

            @Override
            public void handleCategory(CategoryProxy isNull) {
                setResult("Categorys");
            }

            @Override
            public void handleFillBlank(FillBlankProxy isNull) {
                setResult("FillBlanks");
            }

            @Override
            public void handleGlossary(GlossaryProxy isNull) {
                setResult("Glossarys");
            }

            @Override
            public void handleQuiz(QuizProxy isNull) {
                setResult("Quizs");
            }

            @Override
            public void handleRole(RoleProxy isNull) {
                setResult("Roles");
            }

            @Override
            public void handleSingleChoice(SingleChoiceProxy isNull) {
                setResult("SingleChoices");
            }

            @Override
            public void handleTrueOrFalse(TrueOrFalseProxy isNull) {
                setResult("TrueOrFalses");
            }

            @Override
            public void handleUser(UserProxy isNull) {
                setResult("Users");
            }
        }.process(object.getProxyClass());
    }
}
