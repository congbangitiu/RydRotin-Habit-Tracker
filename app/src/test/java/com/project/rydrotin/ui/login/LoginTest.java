package com.project.rydrotin.ui.login;

import static junit.framework.TestCase.assertEquals;
import static org.robolectric.Shadows.shadowOf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.rydrotin.R;
import com.project.rydrotin.ui.habits.HabitsActivity;
import com.project.rydrotin.ui.home.HomeActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;

/** @noinspection deprecation*/
@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    @Test
    public void clickingLogin_shouldStartHomeActivity() {
        try (ActivityController<Login> controller = Robolectric.buildActivity(Login.class)) {
            controller.setup(); // Moves the Activity to the RESUMED state

            Login activity = controller.get();
            EditText email = activity.findViewById(R.id.textEmailAddress);
            EditText password = activity.findViewById(R.id.textPassword);
            email.setText("test@example.com");
            password.setText("password");

            // Mock the database interaction
            DBHelper dbHelper = Mockito.mock(DBHelper.class);
            Mockito.when(dbHelper.checkValidateLogin("test@example.com", "password")).thenReturn(false);
            activity.DB = dbHelper;

            activity.<Button> findViewById(R.id.btn_login).performClick();

            Intent expectedIntent = new Intent(activity, HomeActivity.class);
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            Assert.assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
    }


    @Test
    public void clickingRegis_shouldStartRegisActivity() {
        try (ActivityController<Login> controller = Robolectric.buildActivity(Login.class)) {
            controller.setup(); // Moves the Activity to the RESUMED state

            Login activity = controller.get();

            activity.<TextView> findViewById(R.id.textRegister).performClick();

            Intent expectedIntent = new Intent(activity, Registration.class);
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            Assert.assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
    }
}