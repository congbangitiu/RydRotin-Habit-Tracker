package com.project.rydrotin.ui.login;

import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import org.robolectric.shadows.ShadowLooper;

/** @noinspection deprecation*/
@RunWith(RobolectricTestRunner.class)
public class RegistrationTest extends AppCompatActivity {

    @Test
    public void clickingRegis_shouldSFinishRegisActivity() {
        if (Looper.getMainLooper() == null) {
            Looper.prepareMainLooper();
        }

        try (ActivityController<Registration> controller = Robolectric.buildActivity(Registration.class)) {
            controller.setup(); // Moves the Activity to the RESUMED state

            Registration activity = controller.get();
            EditText email = activity.findViewById(R.id.textEmail);
            EditText username = activity.findViewById(R.id.textUsername);
            EditText pass = activity.findViewById(R.id.textResPassword);
            EditText repass = activity.findViewById(R.id.textResRepassword);
            email.setText("test@example.com");
            username.setText("username");
            pass.setText("123456");
            repass.setText("123456");

            // Mock the database interaction
            DBHelper dbHelper = Mockito.mock(DBHelper.class);
            Mockito.when(dbHelper.checkEmail("test01@example.com")).thenReturn(false);
            Mockito.when(dbHelper.checkUsername("username01")).thenReturn(false);
            activity.DB = dbHelper;

            activity.<Button> findViewById(R.id.btn_register).performClick();
            ShadowLooper.runUiThreadTasks();
            ShadowLooper.idleMainLooper();


            Intent expectedIntent = new Intent(activity, HomeActivity.class);
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            Assert.assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
    }

    @Test
    public void clickingLogin_shouldStartLoginActivity() {
        try (ActivityController<Registration> controller = Robolectric.buildActivity(Registration.class)) {
            controller.setup(); // Moves the Activity to the RESUMED state

            Registration activity = controller.get();

            activity.<TextView> findViewById(R.id.textRegister).performClick();

            Intent expectedIntent = new Intent(activity, Login.class);
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            Assert.assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
    }
}