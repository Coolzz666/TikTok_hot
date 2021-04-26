package com.tiktok.util

import android.widget.Toast
import com.tiktok.MyApplication

fun String.showToast(){
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_SHORT).show()
}