package com.connect.connecticutapp.Articles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.connect.connecticutapp.PostJob;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.dashboard_activity;
import com.connect.connecticutapp.model.Data;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postArticle extends AppCompatActivity {
    TextInputLayout Article_Title,Article_desc,Article_content,articleWriterFullName, articleContact,articleWriterEmail, articleWriterDesc,articleWriterBlogWebsite;
    Button button1;


    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference articlePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_article);


        Article_Title = findViewById(R.id.article_Title);
        Article_desc = findViewById(R.id.article_desc);
        Article_content = findViewById(R.id.article_blog);
        articleWriterDesc = findViewById(R.id.articleWriterBriefIntro);
        articleWriterEmail = findViewById(R.id.articleWriterEmail);
        articleContact = findViewById(R.id.articleWriterPhone);
        articleWriterFullName = findViewById(R.id.articleWriterFullName);
        articleWriterBlogWebsite = findViewById(R.id.articleWebsite);
        articlePost = FirebaseDatabase.getInstance().getReference();
        button1 = findViewById(R.id.post_an_article);





    }



    public void callNextSignupScreen(View view) {
        if (!validateArticleName() | !validateArticleDescription() | !validateArticleContent() ) {
            return;
        }




        String articleTitle = Article_Title.getEditText().getText().toString().trim();
        String job_title = getIntent().getStringExtra("ArticleTitle");
        String articleDescription =Article_desc.getEditText().getText().toString().trim();
        String article_description = getIntent().getStringExtra("ArticleDesc");
        String articleContent = Article_content.getEditText().getText().toString().trim();
        String article_Content = getIntent().getStringExtra("ArticleBlog");
        String writerEmail = articleWriterEmail.getEditText().getText().toString().trim();
        String writerName = articleWriterFullName.getEditText().getText().toString().trim();
        String writerContact = articleContact.getEditText().getText().toString().trim();
        String writerDesc = articleWriterDesc.getEditText().getText().toString().trim();
        String writerWebsite = articleWriterBlogWebsite.getEditText().toString().trim();




        String id = articlePost.push().getKey();
        String timestamp = ""+System.currentTimeMillis();


        ArticlesData articlesdata = new ArticlesData(articleTitle,articleDescription,articleContent, writerEmail, writerName, writerContact, writerDesc, writerWebsite,timestamp);

        articlePost.child("Article").child(id).setValue(articlesdata);







        Intent intent = new Intent(getApplicationContext(), dashboard_activity.class);

        intent.putExtra("articleTitle", articleTitle);
        System.out.println("wewe: "+articleTitle);
        intent.putExtra("articleDescription", articleDescription);
//        intent.putExtra("date", date);
        intent.putExtra("articleContent", articleContent );





        //Add shared Animation
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> (button1, "transition_next_btn_article");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(postArticle.this, pairs);
            startActivity(intent, options.toBundle());
        }else{
            startActivity(intent);
        }
    }
    private boolean validateArticleContent() {
        String val = Article_Title.getEditText().getText().toString().trim();
        // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            Article_Title.setError("Enter valid Job Title");
            return false;
            // } else if (!val.matches(checkspaces)) {
            // phonenumber.setError("No White spaces are allowed!");
            //  return false;
        } else {
            Article_Title.setError(null);
            Article_Title.setErrorEnabled(false);
            return true;
        }


    }

    private boolean validateArticleDescription() {
        String val = Article_desc.getEditText().getText().toString().trim();
        // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            Article_desc.setError("Enter valid Job Title");
            return false;
            // } else if (!val.matches(checkspaces)) {
            // phonenumber.setError("No White spaces are allowed!");
            //  return false;
        } else {
            Article_desc.setError(null);
            Article_desc.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateArticleName() {


        String val = Article_content.getEditText().getText().toString().trim();
        // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            Article_content.setError("Enter valid Job Title");
            return false;
            // } else if (!val.matches(checkspaces)) {
            // phonenumber.setError("No White spaces are allowed!");
            //  return false;
        } else {
            Article_content.setError(null);
            Article_content.setErrorEnabled(false);
            return true;
        }
    }
}