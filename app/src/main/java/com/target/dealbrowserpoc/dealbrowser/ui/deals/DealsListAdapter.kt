package com.target.dealbrowserpoc.dealbrowser.ui.deals
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.utils.TargetLogger
import com.target.dealbrowserpoc.dealbrowser.utils.loadImageUrl
import kotlinx.android.synthetic.main.view_deals_list_item.view.*
import kotlinx.android.synthetic.main.view_list_item_loading.view.*
import java.lang.Exception


class DealsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val DEALS_LIST_ITEM = 1
        const val LOADING = 2
    }

    private val mDeals: MutableList<Deal> = mutableListOf()
    private lateinit var mOnDealSelect: (Deal, Int) -> Unit
    private lateinit var mContext: Context

    fun setContext(context: Context): DealsListAdapter {
        return this.apply {
            mContext = context
        }
    }

    fun setOnDealSelected(onDealSelect: ((Deal, Int) -> Unit)): DealsListAdapter {
        return this.apply {
            mOnDealSelect = onDealSelect
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            LOADING -> DealLoadingViewHolder(
                LayoutInflater.from(mContext)
                    .inflate(R.layout.view_list_item_loading, parent, false)
            )
            else -> DealItemViewHolder(
                LayoutInflater.from(
                    mContext
                ).inflate(R.layout.view_deals_list_item, parent, false),
                onDealSelected = mOnDealSelect
            )
        }
    }

    fun setData(items: MutableList<Deal>?, isLoading: Boolean) {
        if (items == null) return
        mDeals.clear()

        items.forEach{
            it.viewType = DEALS_LIST_ITEM
            mDeals.add(it)
        }

        if (isLoading){
            val deal = Deal()
            deal.viewType = LOADING
            mDeals.add(deal)
        }else{
            if(mDeals.size > 0 && mDeals[mDeals.size.minus(1)].viewType == LOADING){
                mDeals.removeAt(mDeals.size.minus(1))
            }
        }

        notifyDataSetChanged()
        TargetLogger.info("adapter called ${items.size}")
    }


    class DealItemViewHolder(
        view: View,
        val onDealSelected: (Deal, Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, deal: Deal, mContext: Context) {


            itemView.let {

                it.setOnClickListener {
                    println("@#@#clicked")
                    onDealSelected.invoke(deal, position)
                }

                it.tvTitle.text = deal.title
                it.tvAisle.text = deal.aisle
                it.tvSalePrice.text = deal.salePrice?: deal.price
                it.tvShip.text = "ship"
                it.tvMedian.text = "or"

             //   val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/c5/Target_Corporation_logo_%28vector%29.svg"


                try {
                    it.itemImage.loadImageUrl(
                        deal.imageUrl ?: "",
                        roundedCorners = false,
                        placeholder = R.drawable.default_item
                    )

                } catch (e: Exception) {
                   TargetLogger.error(e.localizedMessage)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return (mDeals.get(position).viewType)
    }


    override fun getItemCount(): Int {
        return mDeals.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DealLoadingViewHolder){
            holder.bind()
        }else if(holder is DealItemViewHolder) {
            holder.bind(position, mDeals[position], mContext)
        }
    }

    open class DealLoadingViewHolder(
        var view: View
    ) : RecyclerView.ViewHolder(view) {
        private val shimmerFrameLayout: ShimmerFrameLayout = view.shimmerFrameLayout

        fun bind() {
            shimmerFrameLayout.startShimmer()
        }
    }


}