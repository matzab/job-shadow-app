package hkr.da224a.jobshadow.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import hkr.da224a.jobshadow.model.Business;
import hkr.da224a.jobshadow.model.Student;

public class FirebaseDatabaseHelper {

    private static final String TAG = "FirebaseDatabaseHelper";
    private static Context context;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference studentEndPoint;
    private static DatabaseReference businessEndPoint;
    private static DatabaseReference applicationEndPoint;
    private static DatabaseReference offerEndPoint;
    private static DatabaseReference offerCategoryEndPoint;

    public FirebaseDatabaseHelper(Context con) {
        context = con;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        businessEndPoint = firebaseDatabase.getReference("businessTable");
        studentEndPoint = firebaseDatabase.getReference("studentTable");
        offerEndPoint = firebaseDatabase.getReference("offerTable");
        applicationEndPoint = firebaseDatabase.getReference("applicationTable");
        offerCategoryEndPoint = firebaseDatabase.getReference("offerCategoryTable");
    }


    public static boolean signInNewStudent(final Student student) {
        final boolean[] isAdded = {false};
        final CountDownLatch done = new CountDownLatch(1);
        firebaseAuth.createUserWithEmailAndPassword(student.getEmailAddress(), student.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            done.countDown();
                            studentEndPoint.setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                        isAdded[0] = true;
                                    else {
                                        Objects.requireNonNull(firebaseAuth.getCurrentUser()).delete();
                                    }
                                }
                            });
                        }
                    }
                });
        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isAdded[0];
    }

    public static boolean signInNewBusiness(final Business business) {
        final boolean[] isAdded = {false};
        final CountDownLatch done = new CountDownLatch(1);
        firebaseAuth.createUserWithEmailAndPassword(business.getContactEmail(), business.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            done.countDown();
                            businessEndPoint.setValue(business).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        isAdded[0] = true;
                                    } else {
                                        Objects.requireNonNull(firebaseAuth.getCurrentUser()).delete();
                                    }
                                }
                            });
                        }
                    }
                });
        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isAdded[0];
    }

    public static boolean loginUser(String email, String password) {
        final boolean[] isLogin = {false};
        final CountDownLatch done = new CountDownLatch(1);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e(TAG, task.toString());
                if (task.isSuccessful()) {
                    isLogin[0] = true;
                    done.countDown();
                }
            }
        });
        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isLogin[0];
    }
}
