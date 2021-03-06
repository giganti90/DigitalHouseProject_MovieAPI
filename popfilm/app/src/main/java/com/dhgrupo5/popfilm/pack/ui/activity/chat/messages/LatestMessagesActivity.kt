package com.dhgrupo5.popfilm.messages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.models.ChatMessage
import com.dhgrupo5.popfilm.models.User
import com.dhgrupo5.popfilm.pack.ui.activity.home.HomeActivity
import com.dhgrupo5.popfilm.pack.ui.activity.login.LoginEmailActivity
import com.dhgrupo5.popfilm.pack.ui.activity.login.LoginSignupActivity
import com.dhgrupo5.popfilm.views.LatestMessageRow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_messages.*


class LatestMessagesActivity : AppCompatActivity() {

  companion object {
    var currentUser: User? = null
    val TAG = "LatestMessages"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_latest_messages)

    recyclerview_latest_messages.adapter = adapter
    recyclerview_latest_messages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    // set item click listener on your adapter
    adapter.setOnItemClickListener { item, view ->
      Log.d(TAG, "123")
      val intent = Intent(this, ChatLogActivity::class.java)


      val row = item as LatestMessageRow
      intent.putExtra(NewMessageActivity.USER_KEY, row.chatPartnerUser)
      startActivity(intent)
    }

    listenForLatestMessages()

    fetchCurrentUser()

    verifyUserIsLoggedIn()
  }

  val latestMessagesMap = HashMap<String, ChatMessage>()

  private fun refreshRecyclerViewMessages() {
    adapter.clear()
    latestMessagesMap.values.forEach {
      adapter.add(LatestMessageRow(it))
    }
  }

  private fun listenForLatestMessages() {
    val fromId = FirebaseAuth.getInstance().uid
    val ref = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")
    ref.addChildEventListener(object: ChildEventListener {
      override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
        latestMessagesMap[p0.key!!] = chatMessage
        refreshRecyclerViewMessages()
      }

      override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
        latestMessagesMap[p0.key!!] = chatMessage
        refreshRecyclerViewMessages()
      }

      override fun onChildMoved(p0: DataSnapshot, p1: String?) {

      }
      override fun onChildRemoved(p0: DataSnapshot) {

      }
      override fun onCancelled(p0: DatabaseError) {

      }
    })
  }

  val adapter = GroupAdapter<ViewHolder>()


  private fun fetchCurrentUser() {
    val uid = FirebaseAuth.getInstance().uid
    val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
    ref.addListenerForSingleValueEvent(object: ValueEventListener {

      override fun onDataChange(p0: DataSnapshot) {
        currentUser = p0.getValue(User::class.java)
        Log.d("LatestMessages", "Current user ${currentUser?.profileImageUrl}")
      }

      override fun onCancelled(p0: DatabaseError) {

      }
    })
  }

  private fun verifyUserIsLoggedIn() {
    val uid = FirebaseAuth.getInstance().uid

    if (uid == null) {
      val intent = Intent(this, LoginEmailActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
      startActivity(intent)
      Toast.makeText(this, "Fa??a o Login com email e senha \n para entrar no Chat!",Toast.LENGTH_LONG).show()

    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item?.itemId) {
      R.id.menu_new_message -> {
        val intent = Intent(this, NewMessageActivity::class.java)
        startActivity(intent)
      }
      R.id.menu_sign_out -> {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
      }
    }

    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.nav_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

}