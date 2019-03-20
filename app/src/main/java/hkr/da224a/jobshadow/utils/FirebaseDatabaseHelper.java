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

import hkr.da224a.jobshadow.model.Application;
import hkr.da224a.jobshadow.model.Business;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.model.OfferCategory;
import hkr.da224a.jobshadow.model.Student;

/**
 * The type Firebase database helper.
 */
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

    /**
     * Instantiates a new Firebase database helper.
     *
     * @param con the con
     */
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


    /**
     * Create new student account boolean.
     *
     * @param student the student
     * @return the boolean
     */
    public static boolean createStudent(final Student student) {
        firebaseAuth.createUserWithEmailAndPassword(student.getEmailAddress(), student.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG, "Auth Created");
                            studentEndPoint.child(Integer.toString(student.getStudentID())).
                                    setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.e(TAG, "Database entry created");
                                    } else {
                                        Log.e(TAG, task.getException().toString());
                                        Objects.requireNonNull(firebaseAuth.getCurrentUser()).delete();
                                    }
                                }
                            });
                        } else Log.e(TAG, task.getException().toString());
                    }
                });

        return true;
    }

    public static boolean deleteStudent(final Student student) {
        studentEndPoint.child(Integer.toString(student.getStudentID()))
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.e(TAG, "Child deleted Successfully");
                } else {
                    Log.e(TAG, "Error Deleting child");
                }
            }
        });
        return true;
    }

    /**
     * Create new business boolean.
     *
     * @param business the business
     * @return the boolean
     */
    public static boolean createBusiness(final Business business) {
        final boolean[] isAdded = {false};
        firebaseAuth.createUserWithEmailAndPassword(business.getContactEmail(), business.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            businessEndPoint.child(Integer.toString(business.getBusinessID())).setValue(business).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        isAdded[0] = true;
                                    }
                                }
                            });
                        }
                    }
                });
        return true;
    }

    public static boolean deleteBusiness(final Business business){
        businessEndPoint.child(Integer.toString(business.getBusinessID())).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.e(TAG, "Business Deleted Successfully");
                }
                else {
                    Log.e(TAG, "Error Occurred");
                }
            }
        });
        return true;
    }

    /**
     * Create new application boolean.
     *
     * @param application the application
     * @return the boolean
     */
    public static boolean createNewApplication(final Application application) {
        final boolean[] isCreated = {false};
        applicationEndPoint.child(Integer.toString(application.getOfferID())).setValue(application).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    isCreated[0] = true;
                }
            }
        });
        return isCreated[0];
    }

    /**
     * Create new offer boolean.
     *
     * @param offer the offer
     * @return the boolean
     */
    public static boolean createNewOffer(final Offer offer) {
        final boolean[] isCreated = {false};
        offerEndPoint.child(Integer.toString(offer.getOfferID())).setValue(offer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    isCreated[0] = true;
                }
            }
        });
        return isCreated[0];
    }

    /**
     * Create new offer category boolean.
     *
     * @param offerCategory the offer category
     * @return the boolean
     */
    public static boolean createNewOfferCategory(final OfferCategory offerCategory) {
        final boolean[] isCreated = {false};
        final CountDownLatch done = new CountDownLatch(1);
        offerCategoryEndPoint.child(Integer.toString(offerCategory.getCategoryID())).setValue(offerCategory).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    isCreated[0] = true;
                    done.countDown();
                }
            }
        });
        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isCreated[0];
    }

    /**
     * Login user boolean.
     *
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    public static boolean loginUser(String email, String password) {
        final boolean[] isLogin = {false};
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e(TAG, task.toString());
                if (task.isSuccessful()) {
                    isLogin[0] = true;
                }
            }
        });
        return isLogin[0];
    }
}
