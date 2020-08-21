package com.engineering.dokkan.utils;


import android.view.View;

public interface OnItemClickListener<T> {
    void onItemClicked(View view, T item);
}
