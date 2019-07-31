package com.movie.film.utils;

import android.net.Uri;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.movie.film.fragment.MoviePhotoUpload;

import org.springframework.util.StringUtils;

public class MovieFilmReceiptPicUtil {

    public static final String storage_bucket = "midas-787b6.appspot.com";
    private static StorageReference storageRef;
    private static String downloadUrl;
    private static Uri uploadUrl;
    private static MoviePhotoUpload activity;
    public static final String main_forlder = "midas_expense";
    private MovieFilmReceiptPicUtil profile;

    public static void initializeBucket(MoviePhotoUpload MoviePhotoUploadActivity) {
        try {
            activity = MoviePhotoUploadActivity;
            FirebaseStorage storage = FirebaseStorage.getInstance();
            if (storage != null) {
                // Create a storage reference from our app
                storageRef = storage.getReferenceFromUrl("gs://" + storage_bucket);
                if (storageRef != null) {
                    //get reference to our storage directory
                    storageRef.child(main_forlder);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String uploadPhoto(String photoName, byte[] imageToUpload) {
        if (storageRef != null && !StringUtils.isEmpty(photoName) && imageToUpload != null) {

            // Create a reference to 'pesachoice_ids/photoName'
            final StorageReference pesaProfilePicRef = storageRef.child("midas_expense/" + photoName);
            UploadTask uploadTask = pesaProfilePicRef.putBytes(imageToUpload);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure( Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                   downloadUrl = String.valueOf(taskSnapshot.getDownloadUrl());
                    if (activity != null) {
                        activity.onFinishedPhotoUploading(downloadUrl);
                    }
                }
            });
        }
        return downloadUrl;
    }

}
