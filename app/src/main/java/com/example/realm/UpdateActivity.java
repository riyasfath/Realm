package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class UpdateActivity extends AppCompatActivity {
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt, courseTracksEdt;

    // creating a strings for storing
    // our values from edittext fields.
    private String courseName, courseDuration, courseDescription, courseTracks;
    private long id;
    private Button updateCourseBtn;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            // creating variables for our edit tex
                setContentView(R.layout.activity_update);

                // initializing our edittext and buttons
                realm = Realm.getDefaultInstance();
                courseNameEdt = findViewById(R.id.idEdtCourseName);
                courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
                courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
                courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
                updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);

                // on below line we are getting data which is passed from intent.
                courseName = getIntent().getStringExtra("courseName");
                courseDuration = getIntent().getStringExtra("courseDuration");
                courseDescription = getIntent().getStringExtra("courseDescription");
                courseTracks = getIntent().getStringExtra("courseTracks");
                id = getIntent().getLongExtra("id", 0);

                // on below line we are setting data in our edit text fields.
                courseNameEdt.setText(courseName);
                courseDurationEdt.setText(courseDuration);
                courseDescriptionEdt.setText(courseDescription);
                courseTracksEdt.setText(courseTracks);

                // adding on click listener for update button.
                updateCourseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // getting data from edittext fields.
                        String courseName = courseNameEdt.getText().toString();
                        String courseDescription = courseDescriptionEdt.getText().toString();
                        String courseDuration = courseDurationEdt.getText().toString();
                        String courseTracks = courseTracksEdt.getText().toString();

                        // validating the text fields if empty or not.
                        if (TextUtils.isEmpty(courseName)) {
                            courseNameEdt.setError("Please enter Course Name");
                        } else if (TextUtils.isEmpty(courseDescription)) {
                            courseDescriptionEdt.setError("Please enter Course Description");
                        } else if (TextUtils.isEmpty(courseDuration)) {
                            courseDurationEdt.setError("Please enter Course Duration");
                        } else if (TextUtils.isEmpty(courseTracks)) {
                            courseTracksEdt.setError("Please enter Course Tracks");
                        } else {
                            // on below line we are getting data from our modal where
                            // the id of the course equals to which we passed previously.
                            final DataModel modal = realm.where(DataModel.class).equalTo("id", id).findFirst();
                            updateCourse(modal, courseName, courseDescription, courseDuration, courseTracks);
                        }

                        // on below line we are displaying a toast message when course is updated.
                        Toast.makeText(UpdateActivity.this, "Course Updated.", Toast.LENGTH_SHORT).show();

                        // on below line we are opening our activity for read course activity to view updated course.
                        Intent i = new Intent(UpdateActivity.this, DisplayActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }

            private void updateCourse(DataModel modal, String courseName, String courseDescription, String courseDuration, String courseTracks) {

                // on below line we are calling
                // a method to execute a transaction.
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        // on below line we are setting data to our modal class
                        // which we get from our edit text fields.
                        modal.setCourseDescription(courseDescription);
                        modal.setCourseName(courseName);
                        modal.setCourseDuration(courseDuration);
                        modal.setCourseTracks(courseTracks);

                        // inside on execute method we are calling a method to copy
                        // and update to real m database from our modal class.
                        realm.copyToRealmOrUpdate(modal);
                    }
                });
            }
        }

