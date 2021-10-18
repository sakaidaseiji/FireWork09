//20210723 IE3A23 堺田誠二

package ie3a23_2190454.com.e.firework09
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "MainActivity"
    }
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        // ユーザー登録ボタン
        var btnCreate: Button = findViewById<Button>(R.id.btnCreate) as Button
        btnCreate.setOnClickListener{
            var email = (this.findViewById<EditText>(R.id.txtEmail) as EditText).text.toString()
            var password = (this.findViewById<EditText>(R.id.txtPassword) as EditText).text.toString()
            //Log.i(TAG, String.format("create email=%s, password%s", email, password))
            createUserWithEmailAndPassword(email, password )
        }

        // サインインボタン
        var btnSignIn: Button = findViewById<Button>(R.id.btnSignIn) as Button
        btnSignIn.setOnClickListener{
            var email = (this.findViewById<EditText>(R.id.txtEmail) as EditText).text.toString()
            var password = (this.findViewById<EditText>(R.id.txtPassword) as EditText).text.toString()
            //Log.i(TAG, String.format("signin email=%s, password%s", email, password))
            signInWithEmailAndPassword(email, password)
        }
    }

    override fun onStart() {
        super.onStart()

        var currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    // 画面表示更新
    private fun updateUI(currentUser:FirebaseUser?) {
        //Log.i(TAG, String.format("update ui user=%s", currentUser?.email.toString()))
        (findViewById<TextView>(R.id.lblUser) as TextView).text = currentUser?.email.toString()
    }

    // ユーザー登録処理
    private fun createUserWithEmailAndPassword(email: String, password: String) {
        mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener{
                task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.d(TAG, "createUserWithEmail:success")
                var user = mAuth?.currentUser
                updateUI(user)
            } else {
                Log.w(TAG, "createUserWithEmail:failure")
                Toast.makeText(this, "Authentication failed",Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    // サインイン処理
    private fun signInWithEmailAndPassword(email: String, password: String) {
        mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener{
                task: Task<AuthResult> ->
            if(task.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                val intent = Intent(this, MainActivity2::class.java)
                var user = mAuth?.currentUser
                updateUI(user)
                intent.putExtra("VALUE",lblUser.text.toString())
                startActivity(intent)

            } else {
                Log.w(TAG, "signInWithEmail:failure")
                Toast.makeText(this, "Authentication failed",Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

}