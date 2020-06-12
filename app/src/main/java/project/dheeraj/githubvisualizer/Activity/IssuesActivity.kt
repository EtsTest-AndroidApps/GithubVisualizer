/*
 * MIT License
 *
 * Copyright (c) 2020 Dheeraj Kotwani
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package project.dheeraj.githubvisualizer.Activity

import IssuesModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_issues.*
import project.dheeraj.githubvisualizer.Adapter.IssuesAdapter
import project.dheeraj.githubvisualizer.AppConfig
import project.dheeraj.githubvisualizer.Fragment.Issues.AllIssuesFragment
import project.dheeraj.githubvisualizer.Fragment.Issues.AssignedIssuesFragment
import project.dheeraj.githubvisualizer.Fragment.Issues.CreatedIssuesFragment
import project.dheeraj.githubvisualizer.Fragment.Issues.MentionedIssuesFragment
import project.dheeraj.githubvisualizer.Fragment.Main.FeedsFragment
import project.dheeraj.githubvisualizer.Fragment.Main.HomeFragment
import project.dheeraj.githubvisualizer.Fragment.Main.NotificationsFragment
import project.dheeraj.githubvisualizer.Fragment.Main.ProfileFragment
import project.dheeraj.githubvisualizer.Model.IssuesPagerModel
import project.dheeraj.githubvisualizer.Network.GithubApiClient
import project.dheeraj.githubvisualizer.Network.GithubApiInterface
import project.dheeraj.githubvisualizer.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class IssuesActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)

        sharedPref = getSharedPreferences(AppConfig.SHARED_PREF, Context.MODE_PRIVATE)

        var fragments: ArrayList<IssuesPagerModel> = ArrayList()
        fragments.add(IssuesPagerModel("All", HomeFragment()))
        fragments.add(IssuesPagerModel("All", HomeFragment()))
        fragments.add(IssuesPagerModel("All", HomeFragment()))
        fragments.add(IssuesPagerModel("All", HomeFragment()))


        val adapter = FragmentPagerItemAdapter(supportFragmentManager, FragmentPagerItems.with(this)
            .add("All", AllIssuesFragment::class.java)
            .add("Created", CreatedIssuesFragment::class.java)
            .add("Assigned", AssignedIssuesFragment::class.java)
            .add("Mentioned", MentionedIssuesFragment::class.java)
            .create());

        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.adapter = adapter

        val viewPagerTab =
            findViewById<View>(R.id.scaleTabLayoutIssues) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)


        buttonBack.setOnClickListener {
            onBackPressed()
        }


    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}


