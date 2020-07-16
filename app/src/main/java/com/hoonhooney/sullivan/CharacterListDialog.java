package com.hoonhooney.sullivan;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.lang.reflect.Field;

public class CharacterListDialog extends BottomSheetDialogFragment {
    public static final String TAG = "TAG : CharacterListDialog";

    private Context context;
    private String[] charArr;
    private String style;
    private TextView textView_title;
    private ListView listView_characters;
    private ImageView button_back;

    private CharItemClickListener mListener;

    public CharacterListDialog(Context context, String[] arr, String style){
        this.context = context;
        this.charArr = arr;
        this.style = style;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_bottom_sheet_list, container, false);

        textView_title = view.findViewById(R.id.textView_dialog_title);
        textView_title.setText(style);

        listView_characters = view.findViewById(R.id.listView_characters);
        listView_characters.setAdapter
                (new ArrayAdapter(context, android.R.layout.simple_list_item_1, charArr));

        listView_characters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                char charChosen = ((String)adapterView.getItemAtPosition(i)).charAt(0);
                mListener.onItemClicked(charChosen, style);
                dismiss();
            }
        });

        button_back = view.findViewById(R.id.btn_bottom_sheet_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof CharItemClickListener){
            mListener = (CharItemClickListener) context;
        }else{
            throw new RuntimeException(context.toString()+"must implement CharItemClickListener");
        }
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
        bottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet_list);

        try {
            Field behaviorField = bottomSheetDialog.getClass().getDeclaredField("behavior");
            behaviorField.setAccessible(true);
            final BottomSheetBehavior behavior = (BottomSheetBehavior) behaviorField.get(bottomSheetDialog);
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING){
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    public interface CharItemClickListener{
        void onItemClicked(char result, String style);
    }

}
