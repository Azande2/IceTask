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

        // Find UI Components by their IDs
        val edtBillAmt = findViewById<EditText>(R.id.edtBillAmt)
        val edtTip = findViewById<EditText>(R.id.edtTipPercentage)
        val edtPeople = findViewById<EditText>(R.id.edtPeople)
        val btnCalc = findViewById<Button>(R.id.btnCalculate)
        val txtTip = findViewById<TextView>(R.id.txtTip)
        val txtTotalBill = findViewById<TextView>(R.id.txtTotalBill)
        val txtSplitBill = findViewById<TextView>(R.id.txtSplitBill)
        val txtSplitTip = findViewById<TextView>(R.id.txtSplitTip)

        // Set an onClickListener on the button to perform an action
        btnCalc.setOnClickListener {
            val billAmt = edtBillAmt.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercent = edtTip.text.toString().toDoubleOrNull() ?: 0.0
            val numPeople = edtPeople.text.toString().toIntOrNull() ?: 1 // Default to 1 person

            if (numPeople <= 0) {
                txtTip.text = "Number of people must be at least 1"
                return@setOnClickListener
            }

            val tipAmt = billAmt * (tipPercent / 100)
            val totalBill = billAmt + tipAmt
            val splitBill = totalBill / numPeople
            val splitTip = tipAmt / numPeople

            // Displaying the results
            txtTip.text = "Tip Amount: R${String.format("%.2f", tipAmt)}"
            txtTotalBill.text = "Total Bill: R${String.format("%.2f", totalBill)}"
            txtSplitBill.text = "Each Person Pays (Bill): R${String.format("%.2f", splitBill)}"
            txtSplitTip.text = "Each Person Pays (Tip): R${String.format("%.2f", splitTip)}"
        }
    }
}
