package ie3a23_2190454.com.e.firework09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {
    // フィールド用の変数（カウント数）を定義する
    var count :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        var userID = intent.getStringExtra("VALUE")



        var text = findViewById<TextView>(R.id.textView2)
        text.text = "ID:" + userID
        val text_et = findViewById<EditText>(R.id.text_et)              //EditText（入力エリア）
        val add_btn = findViewById<Button>(R.id.add_btn)                //追加ンボタン

        val text_ll = findViewById<LinearLayout>(R.id.text_ll)          //空LinearLayout
        var userid :String? = text.text.toString()
        val database = FirebaseDatabase.getInstance().getReference("user")
//        //データベースへ値をセット
//        database.(setValue(userID.toString())

        //追加ボタンのクリックイベントを設定
        add_btn.setOnClickListener {
            //テキストビューを生成
            val textView = TextView(this)

            //生成されたテキストに入力されたテキストを代入
            textView.text = text_et.text.toString()

            //テキストビューのサイズを修正
            textView.textSize = 15f

            //ボタン下の空LinearLayoutにテキストビューを追加
            text_ll.addView(textView)

            database.child(count.toString()).setValue(textView.text.toString())
            count++


        }

    }
}