package com.example.fiitqatest.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fiitqatest.databinding.ItemDetailsAttributesBinding
import com.example.fiitqatest.databinding.ItemDetailsDescriptionBinding
import com.example.fiitqatest.databinding.ItemDetailsHeadingBinding
import com.example.fiitqatest.databinding.ItemDetailsHeroBinding
import com.example.fiitqatest.ui.ImageLoader

private const val TYPE_HERO = 1
private const val TYPE_HEADING = 2
private const val TYPE_DESCRIPTION = 3
private const val TYPE_ATTRIBUTES = 4

class DetailsContentAdapter : ListAdapter<DetailsContent, ViewHolder>(DiffCallback()) {

    inner class HeroViewHolder(private val view: ItemDetailsHeroBinding) : ViewHolder(view.root) {
        fun bind(item: DetailsContent.Hero) {
            view.lessonName.text = item.name
            ImageLoader.loadImage(view.coverImage, item.imageUrl)
        }
    }

    inner class HeadingViewHolder(private val view: ItemDetailsHeadingBinding) :
        ViewHolder(view.root) {
        fun bind(item: DetailsContent.Heading) {
            view.root.setText(item.titleRes)
        }
    }

    inner class DescriptionViewHolder(private val view: ItemDetailsDescriptionBinding) :
        ViewHolder(view.root) {
        fun bind(item: DetailsContent.Description) {
            view.root.text = item.description
        }
    }

    inner class AttributesViewHolder(private val view: ItemDetailsAttributesBinding) :
        ViewHolder(view.root) {
        fun bind(item: DetailsContent.Attributes) {
            view.root.text = item.attributes.joinToString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_HERO -> {
                val binding =
                    ItemDetailsHeroBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                HeroViewHolder(binding)
            }
            TYPE_HEADING -> {
                val binding =
                    ItemDetailsHeadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                HeadingViewHolder(binding)
            }

            TYPE_DESCRIPTION -> {
                val binding =
                    ItemDetailsDescriptionBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                DescriptionViewHolder(binding)
            }
            TYPE_ATTRIBUTES -> {
                val binding =
                    ItemDetailsAttributesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                AttributesViewHolder(binding)
            }
            else -> throw IllegalStateException("Unknown item type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HERO -> (holder as HeroViewHolder).bind(
                item = getItem(position) as DetailsContent.Hero
            )
            TYPE_HEADING -> (holder as HeadingViewHolder).bind(
                item = getItem(position) as DetailsContent.Heading
            )
            TYPE_DESCRIPTION -> (holder as DescriptionViewHolder).bind(
                item = getItem(position) as DetailsContent.Description
            )
            TYPE_ATTRIBUTES -> (holder as AttributesViewHolder).bind(
                item = getItem(position) as DetailsContent.Attributes
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DetailsContent.Hero -> TYPE_HERO
            is DetailsContent.Heading -> TYPE_HEADING
            is DetailsContent.Description -> TYPE_DESCRIPTION
            is DetailsContent.Attributes -> TYPE_ATTRIBUTES
            else -> throw IllegalStateException("Unknown item type")
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<DetailsContent>() {
    override fun areItemsTheSame(oldItem: DetailsContent, newItem: DetailsContent): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DetailsContent, newItem: DetailsContent): Boolean {
        return oldItem == newItem
    }
}
