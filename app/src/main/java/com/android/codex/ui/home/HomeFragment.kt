package com.android.codex.ui.home

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.codex.CategoryViewHolder
import com.android.codex.R
import com.android.codex.data.Category
import com.android.codex.databinding.FragmentHomeBinding
import smartadapter.SmartRecyclerAdapter
import smartadapter.diffutil.DiffUtilExtension
import smartadapter.filter.FilterExtension
import smartadapter.get
import smartadapter.viewevent.listener.OnClickEventListener


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: SmartRecyclerAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val predicate = object : DiffUtilExtension.DiffPredicate<Any> {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //assigning items to RecyclerAdapter
        homeViewModel.itemsData.value?.let {
            adapter = SmartRecyclerAdapter
                .items(it)
                .map(Category::class, CategoryViewHolder::class)
                .add(
                    FilterExtension(
                        filterPredicate = { item, constraint ->
                            when (item) {
                                is Category -> item.title.toString().contains(constraint)
                                else -> true
                            }
                        }
                    ) {
                        binding.swipeRefreshLayout.isEnabled =it
                    }
                )
                .setLayoutManager(
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                )
                .add(OnClickEventListener {
                    Toast.makeText(requireContext(), "hahha", Toast.LENGTH_SHORT).show()
                })
                .into<SmartRecyclerAdapter>(binding.recyclerViewHome)
        }
        binding.recyclerViewHome.setHasFixedSize(true)

        return root
    }

    // calling on create option menu
    // layout to inflate our menu file.
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // below line is to get our inflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.home_menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.actionView as SearchView
        val searchEditId = androidx.appcompat.R.id.search_src_text
        val et = searchView.findViewById<View>(searchEditId) as EditText
        et.setTextCursorDrawable(R.drawable.cursor)
        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })
    }

    fun filter(query: String) {
        val filterExtension: FilterExtension = adapter.get()

        filterExtension.filter(lifecycleScope, query, autoSetNewItems = true)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}