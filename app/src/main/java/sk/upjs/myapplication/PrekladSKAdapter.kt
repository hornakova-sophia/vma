package sk.upjs.myapplication
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jazyk.Jazyk
import jazyk.JazykViewModel
import androidx.activity.viewModels

class PrekladSKAdapter(private val listener: OnJazykClickListener) :
    RecyclerView.Adapter<PrekladSKAdapter.JazykViewHolder>() {

    private val jazykyStrings = listOf(
        "category1,slovak1,english1,image1",
        "category2,slovak2,english2,image2",
        "category3,slovak3,english3,image3",
        "category4,slovak4,english4,image4"
    )

    val jazyky = jazykyStrings.map {string ->
        val parts = string.split(',')
        Jazyk(parts[0], parts[1], parts[2], parts[3])
    } as MutableList



    class JazykViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(android.R.id.text1)
        fun bind(jazyk: Jazyk, listener: OnJazykClickListener) {
            textView.text = jazyk.anglicky
            textView.setOnClickListener {
                listener.onJazykClick(jazyk)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JazykViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(
                android.R.layout.simple_list_item_1,
                parent, false
            ).let {
                JazykViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: JazykViewHolder, position: Int) {
        holder.bind(jazyky[position], listener)
    }
    override fun getItemCount() = jazyky.size

    fun remove(index: Int) {
        jazyky.removeAt(index)
        notifyItemRemoved(index)
    }
     }

