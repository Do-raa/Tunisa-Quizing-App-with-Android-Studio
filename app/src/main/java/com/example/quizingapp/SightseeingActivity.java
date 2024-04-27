package com.example.quizingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.quizingapp.components.Question;

public class SightseeingActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioButton radioButtonOption1, radioButtonOption2, radioButtonOption3, radioButtonOption4;
    private Button buttonSubmit;
    private ImageButton buttonNext;
    private ImageButton buttonPrevious;
    private TextView counterView;
    private Question currentQuestion;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private List<Question> questionList;

    private CardView resultCardView;
    private RecyclerView resultRecyclerView;
    private ResultAdapter resultAdapter;
    private List<ResultItem> resultList;
    private int[] selectedAnswers;

    private ImageButton buttonClose;
    private TextView textViewScore;
    private View backgroundBlurView;
    private LinearLayout quizLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sightseeing);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioButtonOption1 = findViewById(R.id.radioButtonOption1);
        radioButtonOption2 = findViewById(R.id.radioButtonOption2);
        radioButtonOption3 = findViewById(R.id.radioButtonOption3);
        radioButtonOption4 = findViewById(R.id.radioButtonOption4);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        counterView = findViewById(R.id.counterView);

        resultCardView = findViewById(R.id.resultCardView);
        resultRecyclerView = findViewById(R.id.resultRecyclerView);
        resultList = new ArrayList<>();
        resultAdapter = new ResultAdapter(resultList);
        resultRecyclerView.setAdapter(resultAdapter);
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonClose = findViewById(R.id.buttonClose);
        textViewScore = findViewById(R.id.textViewScore);
        backgroundBlurView = findViewById(R.id.backgroundBlurView);
        quizLayout = findViewById(R.id.parentLinearLayout);

        // Initialize question list
        questionList = new ArrayList<>();
        questionList.add(new Question("Which ancient city in Tunisia is known for its well-preserved Roman ruins, including the iconic amphitheater?", new String[]{"Carthage", "Dougga", "El Djem", "Kairouan"}, 2));
        questionList.add(new Question("What is the name of the famous historical monument in Tunisia that consists of underground catacombs and ancient burial chambers?", new String[]{"Medina of Tunis","Great Mosque of Kairouan",  "Amphitheatre of Thysdrus", "Catacombs of Milos"}, 3));
        questionList.add(new Question("Which Tunisian city is renowned for its vibrant markets, narrow alleys, and traditional architecture?", new String[]{ "Sousse", "Tozeur","Hammamet", "Sidi Bou Said"}, 3));
        questionList.add(new Question("What is the name of the UNESCO World Heritage Site in Tunisia that features a collection of ancient granaries and underground houses?", new String[]{"Matmata","Kerkouane", "Ichkeul National Park", "Dougga"}, 0));
        questionList.add(new Question("Which coastal town in Tunisia is famous for its white-sand beaches, blue waters, and Mediterranean charm?", new String[]{"Mahdia", "Djerba", "Monastir", "Tabarka"}, 1));
        questionList.add(new Question("What is the name of the archaeological site in Tunisia that contains the ruins of an ancient city destroyed by volcanic eruption?", new String[]{"Utica", "Thuburbo Majus", "Bulla Regia", " Ichkeul National Park"}, 2));
        questionList.add(new Question("Which Tunisian city is known as the \"City of 7 Gates\" and is considered one of the holiest cities in Islam?", new String[]{"Kairouan","Tozeur", "Sfax", "Gafsa"}, 0));

        // Start the Quiz
        displayQuestion();

        // Initialize selectedAnswers
        selectedAnswers = new int[questionList.size()];
        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        // Update the text of counterView whenever currentQuestion changes
        counterView.setText((currentQuestionIndex + 1) + "/7");

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNextAnswer();
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPreviousAnswer();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
                hideQuizLayout();
                blurBackground();
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideResults();
                showQuizLayout();
                unBlurBackground();
            }
        });
    }


    private void displayQuestion() {
        currentQuestion = questionList.get(currentQuestionIndex);
        textViewQuestion.setText(currentQuestion.getQuestion());
        String[] options = currentQuestion.getOptions();
        radioButtonOption1.setText(options[0]);
        radioButtonOption2.setText(options[1]);
        radioButtonOption3.setText(options[2]);
        radioButtonOption4.setText(options[3]);

        // Update the counterView
        counterView.setText((currentQuestionIndex + 1) + "/7");

        // Enable or disable previous and next buttons based on the current question index
        buttonPrevious.setEnabled(currentQuestionIndex > 0);
        buttonNext.setEnabled(currentQuestionIndex < questionList.size() - 1);
    }

    private void checkNextAnswer() {
        int selectedOption = getSelectedOption();
        if (selectedOption == currentQuestion.getAnswerIndex()) {
            score++;
            Toast.makeText(this, "Correct answer !", Toast.LENGTH_SHORT).show();
        } else if (selectedOption != -1) {
            Toast.makeText(this, "Incorrect answer !", Toast.LENGTH_SHORT).show();
        }
        selectedAnswers[currentQuestionIndex] = selectedOption;
        currentQuestionIndex++;
        displayQuestion();
    }

    private void checkPreviousAnswer() {
        int selectedOption = getSelectedOption();
        if (selectedOption == currentQuestion.getAnswerIndex()) {
            score++;
            Toast.makeText(this, "Correct answer !", Toast.LENGTH_SHORT).show();
        } else if (selectedOption != -1) {
            Toast.makeText(this, "Incorrect answer !", Toast.LENGTH_SHORT).show();
        }
        selectedAnswers[currentQuestionIndex] = selectedOption;
        currentQuestionIndex--;
        displayQuestion();
    }

    private int getSelectedOption() {
        if (radioButtonOption1.isChecked()) return 0;
        if (radioButtonOption2.isChecked()) return 1;
        if (radioButtonOption3.isChecked()) return 2;
        if (radioButtonOption4.isChecked()) return 3;
        return -1; // no option selected
    }

    private void showResults() {
        resultCardView.setVisibility(View.VISIBLE);
        resultList.clear();
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            String resultText = "";
            if (selectedAnswers[i] == -1) {
                resultText = "Not answered";
            } else if (question.getAnswerIndex() == selectedAnswers[i]) {
                resultText = "Correct";
            } else if (question.getAnswerIndex() != selectedAnswers[i]) {
                resultText = "Incorrect";
            }
            resultList.add(new ResultItem(i + 1, resultText));
        }
        resultAdapter.notifyDataSetChanged();
        textViewScore.setText("Your Score: " + score + "/" + questionList.size());
    }
    private void hideResults() {
        resultCardView.setVisibility(View.GONE);
    }

    private void hideQuizLayout() {
        quizLayout.setVisibility(View.INVISIBLE);
    }
    private void showQuizLayout() {
        quizLayout.setVisibility(View.VISIBLE);
    }
    private void blurBackground() {
        backgroundBlurView.setVisibility(View.VISIBLE);
    }

    private void unBlurBackground() {
        backgroundBlurView.setVisibility(View.GONE);
    }
    class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

        private List<ResultItem> resultList;

        public ResultAdapter(List<ResultItem> resultList) {
            this.resultList = resultList;
        }

        @NonNull
        @Override
        public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
            return new ResultViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
            ResultItem resultItem = resultList.get(position);
            holder.answerNumber.setText(String.valueOf(resultItem.getAnswerNumber()));
            holder.answerResult.setText(resultItem.getAnswerResult());
        }

        @Override
        public int getItemCount() {
            return resultList.size();
        }

        class ResultViewHolder extends RecyclerView.ViewHolder {

            TextView answerNumber, answerResult;

            public ResultViewHolder(@NonNull View itemView) {
                super(itemView);
                answerNumber = itemView.findViewById(R.id.answerNumber);
                answerResult = itemView.findViewById(R.id.answerResult);
            }
        }
    }

    class ResultItem {
        private int answerNumber;
        private String answerResult;

        public ResultItem(int answerNumber, String answerResult) {
            this.answerNumber = answerNumber;
            this.answerResult = answerResult;
        }

        public int getAnswerNumber() {
            return answerNumber;
        }

        public String getAnswerResult() {
            return answerResult;
        }
    }


}