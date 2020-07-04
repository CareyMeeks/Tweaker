package com.zacharee1.systemuituner.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.mikepenz.materialdrawer.util.ExperimentalNavController
import com.zacharee1.systemuituner.R
import com.zacharee1.systemuituner.dialogs.RoundedBottomSheetDialog
import com.zacharee1.systemuituner.fragments.PersistentFragment
import com.zacharee1.systemuituner.util.addAnimation
import kotlinx.android.synthetic.main.activity_persistent.*

class PersistentActivity : AppCompatActivity() {
    private val persistentFragment by lazy { persistent_fragment as PersistentFragment }
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_persistent)
        setSupportActionBar(toolbar)
        toolbar.addAnimation()

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @ExperimentalNavController
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView?

        searchView?.setOnQueryTextListener(persistentFragment)
        searchView?.addAnimation()

        val addItem = menu.findItem(R.id.add)
        addItem.isVisible = true
        addItem.setOnMenuItemClickListener {
            persistentFragment.addOrEditCustomItem()
            true
        }

        val helpItem = menu.findItem(R.id.help)
        helpItem.isVisible = true
        helpItem.setOnMenuItemClickListener {
            RoundedBottomSheetDialog(this).apply {
                setTitle(R.string.help)
                setMessage(R.string.persistent_options_desc)
                setPositiveButton(android.R.string.ok, null)
                show()
            }
            false
        }

        return true
    }
}