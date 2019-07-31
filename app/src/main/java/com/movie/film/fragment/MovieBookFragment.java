package com.movie.film.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.storage.StorageReference;
import com.movie.film.activities.MovieAsyncListener;
import com.movie.film.activities.MovieMainActivity;
import com.movie.film.activities.R;
import com.movie.film.model.Results;
import com.movie.film.utils.MovieFilmReceiptPicUtil;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieBookFragment extends Fragment implements MoviePhotoUpload, MovieAsyncListener {
    private TextView employee_email;
    private ProgressDialog progressDialog;
    private ImageView receipt_pic;
    private Button receipt_upload;
    private TextView dateTexView;
    private Spinner currencySpinner;
    private EditText amountEditText;
    private EditText descriptionText;
    private TextView image_url;
    private Spinner categorySpinner;
    private Button submitButton;
    private MovieMainActivity activity;
    private Uri file;
    private static final int file_code = 1;
    private String mCurrentPhotoPath;
    public static final int RESULT_OK = -1;
    private Bitmap mImageBitmap;
    private String photoIdName = "";
    private com.movie.film.fragment.MovieBookFragment cameraActivity = null;
    private String url;
    private StorageReference mStorageRef;
    private String path;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment_expense_report, container, false);
        this.setViewInfo(view);
        cameraActivity = this;
       StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //mStorageRef = FirebaseStorage.getInstance().getReference();
        return view;
    }


    private void setViewInfo(View view) {
        employee_email = view.findViewById(R.id.email);
        receipt_pic = view.findViewById(R.id.imageview);
        receipt_upload = view.findViewById(R.id.image_receipt);
        dateTexView = view.findViewById(R.id.date);
        currencySpinner = (Spinner) view.findViewById(R.id.spinner_currency);
        amountEditText = view.findViewById(R.id.total);
        descriptionText = view.findViewById(R.id.comment);
        image_url = view.findViewById(R.id.image_value);
        categorySpinner = (Spinner) view.findViewById(R.id.spinner_category);
        submitButton = view.findViewById(R.id.submit_button);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        String transactionDate = dateFormat.format(new Date());
        dateTexView.setText(transactionDate);
        //receipt_upload.setOnClickListener(new View.OnClickListener() {
            //@Override
          //  public void onClick(View v) {
               // requestPhotoPermission();
           // }
        //});

       /* if (getActivity() instanceof MovieMainActivity) {
            activity = (MovieMainActivity) getActivity();
            String email = activity.getLoggedInUser();
            employee_email.setText(email);
        }*/
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }


    @SuppressLint("NewApi")
    public void onSubmit() {
        String email = employee_email.getText().toString();
        String date = dateTexView.getText().toString();
        String currency = currencySpinner.getSelectedItem().toString();
        String amount = amountEditText.getText().toString();
        String description = descriptionText.getText().toString();
        String type = categorySpinner.getSelectedItem().toString();
        String file = image_url.getText().toString();
        Log.e("photoId:", "before id of a photo");
        //if (sendProfilePicture()) {
           // MovieRequest request = new MovieRequest();
           // Film film = new Film();
            //film.setEmployee(email);
            //film.setData_set("expense");
            //film.setDate_created(date);
            //film.setCurrency(currency);
           // film.setAction("save");
           // film.setDescription(description);
           // film.setReceipt(file);
            //if(amount.matches("[0-9]+")) {
            //    film.setAmount(amount);
            //} else {
               // amountEditText.setText("Please Enter amount in number");
            //}
          //  film.setExpense_type(type);
           // film.setReceipt_url(path);
           // request.setFilm(film);
            //if (getActivity() instanceof MovieMainActivity) {
               // activity = (MovieMainActivity) getActivity();
                //MovieBookController controller = (MovieBookController) MovieControllerFactory.createController(MovieControllerFactory.MovieControllerType.CREATE_EXPENSE, this);
                //Log.e("photoId:", "before id of a photo");
               // if (controller != null) {
                   // Log.e("photoId:", "after id of a photo");
                    //controller.setActivity(this);
                    //controller.setServiceType(MovieClient.MovieServiceType.EXPENSE_CLAIM);
                    //controller.setRequest(request);
                    //controller.execute(request);
               // }
           // }
        //} else {
            //if (getActivity() instanceof MovieMainActivity) {
               // activity = (MovieMainActivity) getActivity();
             //   activity.showMessage("Picture not uploaded. Try again!", "Expense");
           // }

       // }

   // }


   // private void validateaInput() {
   //     if (receipt_pic == null) {


   //     }
   // }
   // @SuppressLint("NewApi")
    //private File getOutputMediaFile() throws IOException {
      //  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
       // String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = Environment.getExternalStoragePublicDirectory(
           //     Environment.DIRECTORY_PICTURES);
        //File image = File.createTempFile(
           //     imageFileName,
             //   ".jpg",
             //   storageDir
       // );
        // Save a file: path for use with ACTION_VIEW intents
       // mCurrentPhotoPath = "file:" + image.getAbsolutePath();
      //  return image;
    //}

    //public void takePicture() throws IOException {
     //   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      //  file = Uri.fromFile(getOutputMediaFile());
       // intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
       // intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      //  startActivityForResult(intent, file_code);

    //}

   // private void requestPhotoPermission() {
        //List<String> permissionNeeded = new ArrayList<>();
        // Here, thisActivity is the current activity
        //if (ContextCompat.checkSelfPermission(getActivity(),
          ///      android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
          //      != PackageManager.PERMISSION_GRANTED) {

          //  permissionNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //}
        //if (ContextCompat.checkSelfPermission(getActivity(),
               // android.Manifest.permission.CAMERA)
                //!= PackageManager.PERMISSION_GRANTED) {
           // permissionNeeded.add(android.Manifest.permission.CAMERA);
        //}
        //if (permissionNeeded != null && permissionNeeded.size() > 0) {
            //ActivityCompat.requestPermissions(getActivity(),
                 //   permissionNeeded.toArray(new String[permissionNeeded.size()]),
                   // file_code);
        //} else {
           // selectImage();
        //}

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                receipt_upload.setEnabled(true);
            }
        }
    }


    private boolean sendProfilePicture() {
        boolean result = false;
        try {
            if (mImageBitmap != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mImageBitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                byte[] byteArray = stream.toByteArray();
                //upload photo to FILEBASE
                MovieFilmReceiptPicUtil.initializeBucket(cameraActivity);
                photoIdName = employee_email.getText().toString() + "_" + System.nanoTime();
                MovieFilmReceiptPicUtil.uploadPhoto(photoIdName, byteArray);
                url = (MovieFilmReceiptPicUtil.uploadPhoto(photoIdName, byteArray));
                 this.onFinishedPhotoUploading(url);
                //url = this.onFinishedPhotoUploading(MovieExpenseReceiptPicUtil.uploadPhoto(photoIdName, byteArray)).
                result = true;

            }
        } catch (Exception e) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            e.printStackTrace();
        }

       // return url;

        return result;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                if (mImageBitmap != null) {
                    this.onStartUploadingPhoto();
                    sendProfilePicture();
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                        image_url.setText("Uploaded Successfully");
                        //receipt_pic.setImageBitmap(mImageBitmap);
                    }

                } else {
                    Toast.makeText(this.getActivity(), "no picture taken", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                mImageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");
                if (mImageBitmap != null) {
                    this.onStartUploadingPhoto();
                    sendProfilePicture();
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                        image_url.setText("Uploaded Successfully");
                        //receipt_pic.setImageBitmap(mImageBitmap);
                    }

                } else {
                    Toast.makeText(this.getActivity(), "no picture taken", Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onStartUploadingPhoto() {
        progressDialog = ProgressDialog.show(getActivity(), "Uploading", "Uploading", true);

    }

    @Override
    public void onFinishedPhotoUploading(String uri) {
        Log.e("ERROR", "PAth");
        if (uri != null) {
            path = uri;
            Log.e("ERROR", path);

        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }
/*    @Override
    public void onClick(View v) {
        View view = getView();
        if (view != null) {
            Button submitButton = (Button) view.findViewById(R.id.submit_button);
            if (v == submitButton) {
                this.onSubmit();
            }
        }


    }*/


    private void selectImage() {
        try {
            if (getActivity() instanceof MovieMainActivity) {
                activity = (MovieMainActivity) getActivity();

                PackageManager pm = activity.getPackageManager();
                int hasPerm = pm.checkPermission(android.Manifest.permission.CAMERA, activity.getPackageName());
                if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                    final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                    builder.setTitle("Select Option");
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            if (options[item].equals("Take Photo")) {
                                dialog.dismiss();
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, PICK_IMAGE_CAMERA);
                            } else if (options[item].equals("Choose From Gallery")) {
                                dialog.dismiss();
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                            } else if (options[item].equals("Cancel")) {
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();
                } else
                    Toast.makeText(this.getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this.getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onTaskStarted() {
        progressDialog = ProgressDialog.show(getActivity(), "Saving", "Expense claim processing", true);

    }

    @Override
    public void onTaskCompleted(ArrayList<Results> data) {


    }

    @Override
    public void onTaskCompleted(Object data) {
        if (data instanceof Results) {
            final Results results = (Results) data;
            Log.e("UserData:", "Response with OBJECT");
            if (results.getErrorMessage() == null) {
                Log.e("UserData:", "Response After OBJECT");
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    if (getActivity() instanceof MovieMainActivity) {
                        activity = (MovieMainActivity) getActivity();

                    }

                    activity.showMessage("Expense Saved Successfully", "Expense");
                }

            } else {
                Log.e("Data:", "Response with Error");
                if (getActivity() instanceof MovieMainActivity) {
                    activity = (MovieMainActivity) getActivity();
                    activity.showMessage(results.getErrorMessage(), "Expense");
                }

            }

        } else {
            Log.e("Final Error:", "OnTaskCompleted");
            if (progressDialog != null) {
                progressDialog.dismiss();

                if (getActivity() instanceof MovieMainActivity) {
                    activity = (MovieMainActivity) getActivity();
                    activity.showMessage("Please fill all the fields", "Expense");
                }
            }

        }


    }
}
