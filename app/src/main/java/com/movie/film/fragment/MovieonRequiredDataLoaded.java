/**
 * Copyright 2015, Pesachoice LLC, All Rights Reserved. The content of this file is sole propriety of Pesachoice LLC.
 * Without formal approval from Pesachoice LLC,  copying, modifying, distributing or altering this file is prohibited.
 */


package com.movie.film.fragment;

import android.view.View;

import com.movie.film.model.Results;


import java.util.ArrayList;


/**
 * used to load necessary data required to setup transanction pcOnPhoneContactLoad
 */
public interface MovieonRequiredDataLoaded {

    public void onDataLoadStart(View view);
    public void onDataLoadFinished();
    public void onCollectionDataLoadFinished(ArrayList<Results> pcData);
}

