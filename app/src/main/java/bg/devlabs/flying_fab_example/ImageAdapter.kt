package bg.devlabs.flying_fab_example
import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Simona Stoyanova on 10/25/17.
 * simona@devlabs.bg
 *
 * Simple RecyclerView.Adapter which shows Image view
 * and loads picture in it from url using Glide.
 */

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    lateinit var images: Array<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_row_item,
                parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("PrivateResource")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.imageView
        Glide.with(imageView.context)
                .load(images[position])
                .apply(RequestOptions()
                        .placeholder(R.color.material_grey_300)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView as ImageView
    }
}