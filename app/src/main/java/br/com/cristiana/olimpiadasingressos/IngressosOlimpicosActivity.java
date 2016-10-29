package br.com.cristiana.olimpiadasingressos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class IngressosOlimpicosActivity extends AppCompatActivity {

    EditText edtNome, edtIdade;
    Spinner spModalidade;
    RadioGroup rdgPeriodo;
    Dialog dialogReserva;

    TextView txtNomeD, txtIdadeD, txtModalidadeD, txtPeriodoD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingressos_olimpicos);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        spModalidade = (Spinner) findViewById(R.id.spModalidade);
        rdgPeriodo = (RadioGroup) findViewById(R.id.rdgPeriodo);

    }

    public void reservar(View v) {

        //Verifica se os campos estão preenchidos
        if (edtNome.getText().toString().equals("")) {
            edtNome.setHint(getString(R.string.app_validacao_nome));//it gives user to hint
            edtNome.setError(getString(R.string.app_validacao_nome));

        }else if (edtIdade.getText().toString().equals("")) {
            edtIdade.setHint(getString(R.string.app_validacao_idade));//it gives user to hint
            edtIdade.setError(getString(R.string.app_validacao_idade));

        }else if(spModalidade.getSelectedItem().equals("--Selecione--")) {
            Toast.makeText(this, getString(R.string.app_validacao_modalidade), Toast.LENGTH_SHORT).show();

        }else if (rdgPeriodo.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, getString(R.string.app_validacao_periodo), Toast.LENGTH_SHORT).show();
        }
        else {
            //cria o dialog
            dialogReserva = new Dialog(this);
            dialogReserva.setTitle(getString(R.string.app_confirmacao));
            dialogReserva.setContentView(R.layout.confirmacao_dialog);

            txtNomeD = (TextView) dialogReserva.findViewById(R.id.txtNomeD);
            txtIdadeD = (TextView) dialogReserva.findViewById(R.id.txtIdadeD);
            txtModalidadeD = (TextView) dialogReserva.findViewById(R.id.txtModalidadeD);
            txtPeriodoD = (TextView) dialogReserva.findViewById(R.id.txtPeriodoD);

            //recupera as informações da tela anterior
            txtNomeD.setText(edtNome.getText());
            txtIdadeD.setText(edtIdade.getText());
            txtModalidadeD.setText(spModalidade.getSelectedItem().toString());

            int idSelecionado = rdgPeriodo.getCheckedRadioButtonId();
            RadioButton rbtPeriodo = (RadioButton) findViewById(idSelecionado);
            txtPeriodoD.setText(rbtPeriodo.getText());

            //Botão de confirmação
            Button button = (Button) dialogReserva.findViewById(R.id.btnConfirmar);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //metódo que vai iniciar a próxima chamada e fechar.
                    confirmar();
                }
            });

            dialogReserva.show();
        }
    }

    public void confirmar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_confirmacao));
        builder.setMessage("Reserva Realizada");

        builder.setPositiveButton(getString(R.string.app_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogReserva.dismiss();

                edtIdade.setText("");
                edtNome.setText("");
                spModalidade.setSelection(0);
                rdgPeriodo.clearCheck();
            }
        });

        builder.show();
    }

}
