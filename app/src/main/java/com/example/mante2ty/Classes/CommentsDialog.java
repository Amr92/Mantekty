package com.example.mante2ty.Classes;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mante2ty.R;
import com.example.mante2ty.ui.StoreDetails;

public class CommentsDialog extends AppCompatDialogFragment {

    private EditText addComment;
    private Button confirm;
    private Button cancel;
    private RatingBar bar;
    int rate;
    private CommentsDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_dialog, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        addComment = view.findViewById(R.id.edit_text_comment);
        confirm = view.findViewById(R.id.post_comment);
        cancel = view.findViewById(R.id.cancel_comment);
        bar = view.findViewById(R.id.comment_rating_bar);

        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //rate = (int) ratingBar.getRating();
                rate = (int) rating;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = addComment.getText().toString();

                if (comment.isEmpty()) {
                    Toast.makeText(getContext(), "Add comment first..", Toast.LENGTH_SHORT).show();
                } else {

                    listener.applyTexts(comment,rate);
                    alertDialog.dismiss();
                }

            }
        });


        return alertDialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (CommentsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +"Must implement DialogListener");
        }
    }

    public interface CommentsDialogListener{
        void applyTexts(String comment,int rating);
    }
}
