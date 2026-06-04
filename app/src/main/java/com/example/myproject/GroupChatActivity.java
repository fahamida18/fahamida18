package com.example.myproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GroupChatActivity extends AppCompatActivity {

    TextView groupInfo, chatMessages;
    EditText messageInput;

    Button sendBtn, backBtn;

    String allMessages = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        groupInfo = findViewById(R.id.groupInfo);
        chatMessages = findViewById(R.id.chatMessages);
        messageInput = findViewById(R.id.messageInput);

        sendBtn = findViewById(R.id.sendBtn);
        backBtn = findViewById(R.id.backBtn);

        // Get data from previous activity
        String groupName = getIntent().getStringExtra("groupName");
        String members = getIntent().getStringExtra("members");

        groupInfo.setText("Group: " + groupName + "\nMembers: " + members);

        // Send Message
        sendBtn.setOnClickListener(v -> {

            String msg = messageInput.getText().toString().trim();

            if (!msg.isEmpty()) {

                allMessages += "You: " + msg + "\n";

                chatMessages.setText(allMessages);

                messageInput.setText("");
            }
        });

        // Back Button
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }
}