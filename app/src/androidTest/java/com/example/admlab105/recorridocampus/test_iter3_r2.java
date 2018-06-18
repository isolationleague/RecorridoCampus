package com.example.admlab105.recorridocampus;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class test_iter3_r2 {

    @Rule
    public ActivityTestRule<StartScreen> mActivityTestRule = new ActivityTestRule<>(StartScreen.class);

    @Test
    public void test_iter3_r2() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.buttonClose),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        button.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction actionBar$Tab = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tabs),
                                0),
                        0),
                        isDisplayed()));
        actionBar$Tab.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tabs),
                                0),
                        1),
                        isDisplayed()));
        actionBar$Tab2.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withContentDescription("Más opciones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.btnCerca),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.btnUser),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.btnCampus),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.frameLayout),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.frameLayout),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        frameLayout2.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction actionBar$Tab3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.info_tabLayout),
                                0),
                        0),
                        isDisplayed()));
        actionBar$Tab3.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.info_tabLayout),
                                0),
                        1),
                        isDisplayed()));
        actionBar$Tab4.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab5 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.info_tabLayout),
                                0),
                        2),
                        isDisplayed()));
        actionBar$Tab5.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withParent(allOf(withId(R.id.view_pager),
                        childAtPosition(
                                withId(R.id.constraintLayout),
                                0))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.playButton),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                1),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction seekBar = onView(
                allOf(withId(R.id.seekBar),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                2),
                        isDisplayed()));
        seekBar.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView), withText("La Facultad de Ciencias Económicas fue creada el 6 de marzo de 1943 y se inauguró el 3 de mayo de ese año, en conmemoración del centenario de la Casa de Enseñanza de Santo Tomás. Como gesto simbólico, el 3 de mayo de 1960 se inauguró su nuevo edificio en el campus. Esta edificación significó la apertura del área de las Ciencias Sociales en la ciudad universitaria. El actual edificio se construyó en tres etapas desde 1960 hasta el 2001. La primera etapa responde a la arquitectura moderna o estilo internacional, caracterizada por el uso del concreto, forma de cajón, manejo de losas en cubiertas, presencia de parasoles y ausencia de aleros. Poseía un mural geométrico en mosaicos en la pared externa del vestíbulo, el cual fue eliminado."),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                3),
                        isDisplayed()));
        textView.check(matches(withText("La Facultad de Ciencias Económicas fue creada el 6 de marzo de 1943 y se inauguró el 3 de mayo de ese año, en conmemoración del centenario de la Casa de Enseñanza de Santo Tomás. Como gesto simbólico, el 3 de mayo de 1960 se inauguró su nuevo edificio en el campus. Esta edificación significó la apertura del área de las Ciencias Sociales en la ciudad universitaria. El actual edificio se construyó en tres etapas desde 1960 hasta el 2001. La primera etapa responde a la arquitectura moderna o estilo internacional, caracterizada por el uso del concreto, forma de cajón, manejo de losas en cubiertas, presencia de parasoles y ausencia de aleros. Poseía un mural geométrico en mosaicos en la pared externa del vestíbulo, el cual fue eliminado.")));

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.constraintLayout),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.constraintLayout01),
                                        0),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction viewGroup2 = onView(
                allOf(withId(R.id.info_content),
                        childAtPosition(
                                allOf(withId(R.id.frameLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withContentDescription("Navegar hacia arriba"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                0),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab6 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tabs),
                                0),
                        0),
                        isDisplayed()));
        actionBar$Tab6.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab7 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tabs),
                                0),
                        1),
                        isDisplayed()));
        actionBar$Tab7.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(withContentDescription("Más opciones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction imageView4 = onView(
                allOf(withParent(allOf(withId(R.id.view_pager),
                        childAtPosition(
                                withId(R.id.constraintLayout),
                                0))),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.playButton),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatImageButton.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction imageButton6 = onView(
                allOf(withId(R.id.playButton),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                1),
                        isDisplayed()));
        imageButton6.check(matches(isDisplayed()));

        ViewInteraction seekBar2 = onView(
                allOf(withId(R.id.seekBar),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                2),
                        isDisplayed()));
        seekBar2.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.playButton),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatImageButton2.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.playButton),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatImageButton3.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.playButton),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatImageButton4.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction tabView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.info_tabLayout),
                                0),
                        1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.linksView), isDisplayed()));
        appCompatTextView.perform(replaceText("• Escuela de Ciencias de la Computación e Informatica \n\n• Oficina de Registro e Informacion \n\n• Museo +UCR \n\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction gridView = onView(
                allOf(withId(R.id.grid_images),
                        childAtPosition(
                                withParent(withId(R.id.info_container)),
                                0),
                        isDisplayed()));
        gridView.check(matches(isDisplayed()));

        ViewInteraction imageView5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.grid_images),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0)),
                        0),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction imageView6 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.grid_images),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0)),
                        4),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction imageView7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.grid_images),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0)),
                        4),
                        isDisplayed()));
        imageView7.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction imageView8 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_images),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                0)))
                .atPosition(1);
        imageView8.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction imageView9 = onView(
                allOf(withId(R.id.full_image_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        imageView9.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction tabView2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.info_tabLayout),
                                0),
                        2),
                        isDisplayed()));
        tabView2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.bubble_description), withText("Está a 6 mts. de distancia"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Está a 6 mts. de distancia")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.bubble_title), withText("Edificio de la Facultad de Ciencias Económicas"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Edificio de la Facultad de Ciencias Económicas")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.linksView), withText("• Escuela de Ciencias de la Computación e Informatica \n\n• Oficina de Registro e Informacion \n\n• Museo +UCR \n\n"),
                        childAtPosition(
                                withParent(withId(R.id.info_container)),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("• Escuela de Ciencias de la Computación e Informatica   • Oficina de Registro e Informacion   • Museo +UCR   ")));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.linksView), withText("• Escuela de Ciencias de la Computación e Informatica \n\n• Oficina de Registro e Informacion \n\n• Museo +UCR \n\n"),
                        childAtPosition(
                                withParent(withId(R.id.info_container)),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("• Escuela de Ciencias de la Computación e Informatica   • Oficina de Registro e Informacion   • Museo +UCR   ")));

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.linksView), withText("• Escuela de Ciencias de la Computación e Informatica \n\n• Oficina de Registro e Informacion \n\n• Museo +UCR \n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.info_container),
                                        1),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
