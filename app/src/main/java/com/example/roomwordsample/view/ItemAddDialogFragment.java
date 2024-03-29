package com.example.roomwordsample.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.R;

public class ItemAddDialogFragment extends DialogFragment {

    private WordViewModel mWordViewModel;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Viewモデルを作成
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        EditText editItem = new EditText(getContext());
        editItem.setHint("add a new item");
        editItem.setMaxLines(1);
        builder.setView(editItem)
                .setTitle("アイテムを追加")
                .setPositiveButton("追加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(editItem.getText())){
                            Toast.makeText(
                                    getContext(),
                                    R.string.empty_not_saved_word,
                                    Toast.LENGTH_LONG
                            ).show();
                        }else {
                            Word word = new Word(editItem.getText().toString());
                            mWordViewModel.insert(word);
                        }
                    }
                })
                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}