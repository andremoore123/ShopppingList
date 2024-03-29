package com.example.myrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerview.adapter.HeroAdapter
import com.example.myrecyclerview.databinding.FragmentFollowerBinding
import com.example.myrecyclerview.viewmodel.FollowersViewModel


class FollowerFragment : Fragment() {
    companion object {
        private const val ARG_USERNAME = "username"

        fun newInstance(username: String?) : FollowerFragment {
            val fragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding : FragmentFollowerBinding? = null
    private lateinit var adapter: HeroAdapter
    private val binding get() = _binding!!
    private lateinit var followersViewModel: FollowersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRecyclerView()
        showLoading(true)
        setFollower()
    }

    private fun showRecyclerView() {
        adapter = HeroAdapter()
        adapter.notifyDataSetChanged()
        binding.rvFollow.layoutManager = LinearLayoutManager(activity)
        binding.rvFollow.adapter = adapter
    }

    private fun setFollower() {
        if(arguments != null) {
            val username = arguments?.getString(ARG_USERNAME)
            followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)

            if (username != null) {
                followersViewModel.setFollowers(username)
            }

            followersViewModel.getFollowers().observe(viewLifecycleOwner) { userItems ->
                if (userItems != null) {
                    adapter.setData(userItems)
                    showLoading(false)
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visible()
        } else {
            binding.progressBar.gone()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }


}