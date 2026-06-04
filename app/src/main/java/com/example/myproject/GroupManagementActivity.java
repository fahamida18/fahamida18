package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GroupManagementActivity extends AppCompatActivity {

    EditText groupNameInput, memberNameInput;

    Button createGroupBtn, addMemberBtn, backBtn;

    ListView memberListView;

    ArrayList<String> memberList;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_management);

        // Initialize Views
        groupNameInput = findViewById(R.id.groupNameInput);

        memberNameInput = findViewById(R.id.memberNameInput);

        createGroupBtn = findViewById(R.id.createGroupBtn);

        addMemberBtn = findViewById(R.id.addMemberBtn);

        memberListView = findViewById(R.id.memberListView);

        backBtn = findViewById(R.id.backBtn); // ✅ NEW BACK BUTTON

        // Member Array List
        memberList = new ArrayList<>();

        // Adapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                memberList
        );

        memberListView.setAdapter(adapter);

        memberListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Add Member Button
        addMemberBtn.setOnClickListener(v -> {

            String memberName =
                    memberNameInput.getText().toString().trim();

            if (memberName.isEmpty()) {

                Toast.makeText(
                        GroupManagementActivity.this,
                        "Enter member name",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            memberList.add(memberName);

            adapter.notifyDataSetChanged();

            memberNameInput.setText("");
        });

        // Create Group Button
        createGroupBtn.setOnClickListener(v -> {

            String groupName =
                    groupNameInput.getText().toString().trim();

            if (groupName.isEmpty()) {

                Toast.makeText(
                        GroupManagementActivity.this,
                        "Enter group name",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ArrayList<String> selectedMembers =
                    new ArrayList<>();

            for (int i = 0; i < memberList.size(); i++) {

                if (memberListView.isItemChecked(i)) {

                    selectedMembers.add(memberList.get(i));
                }
            }

            if (selectedMembers.isEmpty()) {

                Toast.makeText(
                        GroupManagementActivity.this,
                        "Select at least one member",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Intent intent = new Intent(
                    GroupManagementActivity.this,
                    GroupChatActivity.class
            );

            intent.putExtra("groupName", groupName);

            String members = "";

            for (String s : selectedMembers) {
                members += s + ", ";
            }

            intent.putExtra("members", members);

            startActivity(intent);
        });

        // ✅ BACK BUTTON CLICK
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }
}

