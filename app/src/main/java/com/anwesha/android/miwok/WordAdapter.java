package com.anwesha.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.anwesha.android.miwok.R;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    // Resource ID for background color of this list of words
    private int mColorResourceID;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param words   A List of Word objects to display in a list
     * @param colorResourceID Color Resource ID for current list of words
     */
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceID) {
        super(context, 0, words);
        mColorResourceID = colorResourceID;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        /*
          Get the {@link Word} object located at this position in the list
         */
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation from the current Word object and set this text on the name TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the current Word object and set this text on the number TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {
            // Get the image from the current Word object and set this image on the imageView
            imageView.setImageResource(currentWord.getImageResourceID());
            // Make sure the View is VISIBLE
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the imageView(set Visibility to GONE)
            imageView.setVisibility(View.GONE);
        }


        //Set the theme color for the list item
        View textContainer=listItemView.findViewById(R.id.text_container);
        //Find the color that the Resource ID maps to
        int color = ContextCompat.getColor(getContext(),mColorResourceID);
        //Set the background color of the text Container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView) so that it can be shown in the ListView
        return listItemView;
    }

}
