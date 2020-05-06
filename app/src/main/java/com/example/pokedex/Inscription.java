package com.example.pokedex;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Toast;

    import com.android.volley.RequestQueue;
    import com.example.safelockproject.controleur.MyRequest;
    import com.example.safelockproject.R;
    import com.example.safelockproject.controleur.VolleySingleton;
    import com.google.android.material.textfield.TextInputLayout;



    import androidx.appcompat.app.AppCompatActivity;

    import java.util.Map;

    public class Inscription extends AppCompatActivity {

        private Button btnSend;
        private TextInputLayout til_pseudo, til_email, til_password1, til_password2;
        private RequestQueue queue;
        private MyRequest request;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inscription);

            btnSend = (Button) findViewById(R.id.btn_send);

            til_pseudo = (TextInputLayout) findViewById(R.id.til_pseudo_reg);
            til_email = (TextInputLayout) findViewById(R.id.til_email_reg);
            til_password1 = (TextInputLayout)  findViewById(R.id.til_password1_reg);
            til_password2 = (TextInputLayout) findViewById(R.id.til_password2_reg);




            queue = VolleySingleton.getInstance(this).getRequestQueue();
            request = new MyRequest(this, queue);

            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    String nom = til_pseudo.getEditText().getText().toString().trim();
                    String mail = til_email.getEditText().getText().toString().trim();
                    String pw1 = til_password1.getEditText().getText().toString().trim();
                    String pw2 = til_password2.getEditText().getText().toString().trim();


                    if(nom.length()>0 && mail.length()>0 && pw1.length() >0 && pw2.length() >0){

                        request.register(nom, mail, pw1, pw2, new MyRequest.RegisterCallBack() {
                            @Override
                            public void onSuccess(String message) {

                                Intent intent = new Intent(getApplicationContext(), Connexion.class);
                                intent.putExtra("REGISTER", message);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void inputErrors(Map<String, String> errors) {


                                if(errors.get("pseudo") != null){
                                    til_pseudo.setError(errors.get("pseudo"));
                                }else{
                                    til_pseudo.setErrorEnabled(false);
                                }

                                if(errors.get("email") != null){
                                    til_email.setError(errors.get("email"));
                                }else{
                                    til_email.setErrorEnabled(false);
                                }

                                if(errors.get("password") != null){
                                    til_password1.setError(errors.get("password"));
                                }else{
                                    til_password1.setErrorEnabled(false);
                                }

                            }

                            @Override
                            public void onError(String message) {

                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
