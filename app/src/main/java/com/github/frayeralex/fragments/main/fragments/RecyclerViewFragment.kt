package com.github.frayeralex.fragments.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.frayeralex.fragments.R
import com.github.frayeralex.fragments.main.interfaces.CarDataProviderInterface
import com.github.frayeralex.fragments.main.adapters.CustomAdapter
import com.github.frayeralex.fragments.main.interfaces.SelectCarHandlerInterface
import com.github.frayeralex.fragments.models.CarModel

class RecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var carList: ArrayList<CarModel>
    private val handlerContext by lazy { context as? SelectCarHandlerInterface }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.recycler_view_frag,
            container, false
        ).apply { tag =
            TAG
        }

        recyclerView = rootView.findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(activity)

        setRecyclerViewLayoutManager()

        val handlerInterface = activity as? SelectCarHandlerInterface

        if (handlerInterface != null) {
            recyclerView.adapter = CustomAdapter(carList){handlerContext?.onCarSelect(it)}
        }

        return rootView
    }

    private fun setRecyclerViewLayoutManager() {
        var scrollPosition = 0

        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        layoutManager = LinearLayoutManager(activity)

        with(recyclerView) {
            layoutManager = this@RecyclerViewFragment.layoutManager
            scrollToPosition(scrollPosition)
        }

    }

    private fun initData() {
        (activity as? CarDataProviderInterface)?.let {
            carList = it.getCarListData()
        }
    }

    companion object {
        private val TAG = "RecyclerViewFragment"
    }
}