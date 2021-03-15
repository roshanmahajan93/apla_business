package com.prishan.aplabusiness.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.prishan.aplabusiness.data.model.requestmodels.RmLoginRequest;
import com.prishan.aplabusiness.data.network.NetworkOperations;
import com.prishan.aplabusiness.data.network.WebServiceCalls;
import com.prishan.aplabusiness.ui.MainActivity;
import com.prishan.aplabusiness.R;
import com.prishan.aplabusiness.util.Constant;
import com.prishan.aplabusiness.util.SharedPrefrenceObj;
import com.prishan.aplabusiness.util.commonui.PopMessage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    SignInButton signInButton;
    AppCompatButton sign_out_button,upload_button,download_button;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;

    private String dbPath = "/data/data/com.prishan.aplabusiness/databases/studentdb";
    private String dbPathShm = "/data/data/com.prishan.aplabusiness/databases/studentdb-shm";
    private String dbPathWal = "/data/data/com.prishan.aplabusiness/databases/studentdb-wal";

    com.google.api.services.drive.Drive googleDriveService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mStatusTextView = (TextView) findViewById(R.id.mStatusTextView);
        signInButton = findViewById(R.id.sign_in_button);
        upload_button = findViewById(R.id.upload_button);
        download_button = findViewById(R.id.download_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        sign_out_button = findViewById(R.id.sign_out_button);

        sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Uploadasync().execute();
            }
        });
        download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Downloadasync().execute();
            }
        });

        //checkLogin("admin","admin@123");
    }



    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    public class Uploadasync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            upload();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

    public class Downloadasync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            Download();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

    private void checkLogin(final String username, final String password) {
        RmLoginRequest loginRequest = new RmLoginRequest(username, password);
        WebServiceCalls.Login.check(LoginActivity.this, loginRequest, new NetworkOperations(true) {
            @Override
            public void onSuccess(Bundle msg) {
                SharedPrefrenceObj.setSharedValue(
                        LoginActivity.this,
                        Constant.SharedPrefrence.User.USER,
                        (msg.getSerializable(Constant.SharedPrefrence.User.USER)).toString()
                );
                SharedPrefrenceObj.setSharedValue(
                        LoginActivity.this,
                        Constant.SharedPrefrence.User.USERNAME,
                        username
                );
                SharedPrefrenceObj.setSharedValue(
                        LoginActivity.this,
                        Constant.SharedPrefrence.User.PASSWORD,
                        password
                );
                initiateNextActivity();
            }

            @Override
            public void onFailure(Bundle msg) {
                String message = msg.getString(Constant.MESSAGE);
                System.err.println(message);
                PopMessage.makeLongToast(LoginActivity.this, message);
                /*if (message.equalsIgnoreCase(Constant.INVALID_SESSION_MESSAGE)) {
                    Application.goToSessionActivity(LoginActivity.this);
                }*/
            }
        });
    }

    private void initiateNextActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            mStatusTextView.setText(account.getDisplayName());

            GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
            GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(this, Collections.singleton(Scopes.DRIVE_FILE));
            credential.setSelectedAccount(googleSignInAccount.getAccount());
            googleDriveService = new Drive.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new GsonFactory(),
                    credential)
                    .setApplicationName(getString(R.string.app_name))
                    .build();
        } else {
            mStatusTextView.setText("Sign out");
        }
    }

    private void upload(){
        File storageFile = new File();
        storageFile.setParents(Collections.singletonList("appDataFolder"));
        storageFile.setName("studentdb");

        File storageFileShm = new File();
        storageFileShm.setParents(Collections.singletonList("appDataFolder"));
        storageFileShm.setName("studentdb-shm");

        File storageFileWal = new File();
        storageFileWal.setParents(Collections.singletonList("appDataFolder"));
        storageFileWal.setName("studentdb-wal");

        java.io.File filePath = new java.io.File(dbPath);
        java.io.File filePathShm = new java.io.File(dbPathShm);
        java.io.File filePathWal = new java.io.File(dbPathWal);
        FileContent mediaContent = new FileContent("",filePath);
        FileContent mediaContentShm = new FileContent("",filePathShm);
        FileContent mediaContentWal = new FileContent("",filePathWal);
        try {
            File file = googleDriveService.files().create(storageFile, mediaContent).execute();
            Log.e("Filename: %s ID: %s \n", file.getName()+","+ file.getId());

            File fileShm = googleDriveService.files().create(storageFileShm, mediaContentShm).execute();
            Log.e("Filename: %s ID: %s \n", fileShm.getName() +","+ fileShm.getId());

            File fileWal = googleDriveService.files().create(storageFileWal, mediaContentWal).execute();
            Log.e("Filename: %s ID: %s \n", fileWal.getName() +","+ fileWal.getId());
        }

        catch(UserRecoverableAuthIOException e){
            startActivityForResult(e.getIntent(), 1);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    private void Download(){
        try {
            java.io.File dir = new java.io.File("/data/data/com.prishan.aplabusiness/databases");
            if (dir.isDirectory())
            {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++)
                {
                    new java.io.File(dir, children[i]).delete();
                }
            }

            FileList files = null;
            try {
                files = googleDriveService.files().list()
                        .setSpaces("appDataFolder")
                        .setFields("nextPageToken, files(id, name, createdTime)")
                        .setPageSize(10)
                        .execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(files.getFiles().size() == 0)
                Log.e(TAG,"No DB file exists in Drive");
            for (File file : files.getFiles()) {
                System.out.printf("Found file: %s (%s) %s\n",
                        file.getName(), file.getId(), file.getCreatedTime());
                if(file.getName().equals("studentdb")){
                    OutputStream outputStream = new FileOutputStream(dbPath);
                    googleDriveService.files().get(file.getId()).executeMediaAndDownloadTo(outputStream);
                }
                else if(file.getName().equals("studentdb-shm")){
                    OutputStream outputStream = new FileOutputStream(dbPathShm);
                    googleDriveService.files().get(file.getId()).executeMediaAndDownloadTo(outputStream);
                }
                else if(file.getName().equals("studentdb-wal")){
                    OutputStream outputStream = new FileOutputStream(dbPathWal);
                    googleDriveService.files().get(file.getId()).executeMediaAndDownloadTo(outputStream);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }


}