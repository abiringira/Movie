package com.movie.film.fragment;

import android.net.Uri;

public interface MoviePhotoUpload {
    void onStartUploadingPhoto();
    void onFinishedPhotoUploading(String uri);
}
