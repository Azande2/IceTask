package vcmsa.projects.ice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //find UI Componets by thier ids
        val  edtBillAmt = findViewById<EditText>(R.id.edtBillAmt)
        val edtTip = findViewById<EditText>(R.id.edtTipPercentage)
        val btnCalc = findViewById<Button>(R.id.btnCalculate)
        val txtTip = findViewById<TextView>(R.id.txtTip)


        //set an onClickListener on the button to preform an action
        btnCalc.setOnClickListener{
            val billAmt = edtBillAmt.text.toString().toDoubleOrNull()?:0.0
            val tipPercent = edtTip.text.toString().toDoubleOrNull()?:0.0
            val tipAmt = billAmt*(tipPercent/100)

            //displaying the results
            txtTip.text = "Tip Amount: R${String.format("%.2f", tipAmt)}"
        }
    }
}