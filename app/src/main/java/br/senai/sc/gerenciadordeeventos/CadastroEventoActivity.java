package br.senai.sc.gerenciadordeeventos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Calendar;

import br.senai.sc.gerenciadordeeventos.modelo.Evento;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;

public class CadastroEventoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EVENTO_EXCLUIDO = 12;

    private boolean edicao = false;
    private int id = 0;
    private boolean exclusao = false;
    private String valorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Eventos");
        carregarEvento();

        Button btn_data = (Button) findViewById(R.id.btn_data);
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String valorData = DateFormat.getDateInstance().format(c.getTime());
        TextView show_data = (TextView) findViewById(R.id.show_data);
        show_data.setText(valorData);
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNomeEvento = findViewById(R.id.editText_nomeEvento);
            TextView textViewData = findViewById(R.id.show_data);
            EditText editTextLocalEvento = findViewById(R.id.editText_localEvento);

            textViewData.setText(evento.getDataEvento());
            editTextNomeEvento.setText(evento.getNomeEvento());
            editTextLocalEvento.setText(evento.getLocalEvento());

            edicao = true;
            id = evento.getId();
            exclusao = true;
        }
    }

    public void onCLickVoltar(View v) {
        finish();
    }


    public void onCLickSalvar(View v) {
        EditText editText_nomeEvento = findViewById(R.id.editText_nomeEvento);
        TextView textViewData = findViewById(R.id.show_data);
        EditText editText_localEvento = findViewById(R.id.editText_localEvento);

        String nomeEvento = editText_nomeEvento.getText().toString();
        String dataEvento = textViewData.getText().toString();
        String localEvento = editText_localEvento.getText().toString();

        Evento evento = new Evento(id, nomeEvento, dataEvento, localEvento);
        Intent intent = new Intent();

        if (nomeEvento.equals("")) {
            Toast.makeText(CadastroEventoActivity.this, "O campo NOME é obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        if (dataEvento.equals("")) {
            Toast.makeText(CadastroEventoActivity.this, "O campo DATA é obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        if (localEvento.equals("")) {
            Toast.makeText(CadastroEventoActivity.this, "O campo LOCAL é obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        if (edicao) {
            intent.putExtra("eventoEditado", evento);
            setResult(RESULT_CODE_EVENTO_EDITADO, intent);
        } else {
            intent.putExtra("novoEvento", evento);
            setResult(RESULT_CODE_NOVO_EVENTO, intent);
        }

        finish();
    }

    public void onClickExcluir(View v){

        EditText editText_nomeEvento = findViewById(R.id.editText_nomeEvento);
        TextView textViewData = findViewById(R.id.show_data);
        EditText editText_localEvento = findViewById(R.id.editText_localEvento);

        String nomeEvento = editText_nomeEvento.getText().toString();
        String dataEvento = textViewData.getText().toString();
        String localEvento = editText_localEvento.getText().toString();

        Evento eventoEditado = new Evento(id, nomeEvento, dataEvento, localEvento);
        Intent intent = new Intent();

        if (exclusao == true) {
            intent.putExtra("eventoExcluido", eventoEditado);
            setResult(RESULT_CODE_EVENTO_EXCLUIDO, intent);
            Toast.makeText(CadastroEventoActivity.this, "Evento excluído com sucesso!", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(CadastroEventoActivity.this, "Erro ao excluir evento!", Toast.LENGTH_LONG).show();
        }
        finish();

    }

}