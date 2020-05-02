package mx.itesm.secondpartial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MailFragment extends Fragment {

    private View view;
    private EditText subject;
    private EditText message;
    private Button sendMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.mail_fragment, container, false);

        subject = view.findViewById(R.id.mailSubject);
        message = view.findViewById(R.id.mailMessage);
        sendMessage = view.findViewById(R.id.sendButton);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{"fernando.mtzt99@gmail.com"});
                it.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                it.putExtra(Intent.EXTRA_TEXT,message.getText());
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it,"Choose Mail App"));
            }
        });

        return view;
    }
}
