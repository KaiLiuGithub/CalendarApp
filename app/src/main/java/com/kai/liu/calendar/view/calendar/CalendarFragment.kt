package com.kai.liu.calendar.view.calendar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.liu.calendar.database.MemoItem
import kotlinx.android.synthetic.main.calendar_cell_view.view.*
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.memo_view.view.*
import java.time.Month

class CalendarFragment: CalendarBaseFragment() {

    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var calendarViewAdapter: CalendarAdapter
    private lateinit var calendarViewManager: RecyclerView.LayoutManager
    
    private lateinit var memoRecyclerView: RecyclerView
    private lateinit var memoViewAdapter: MemoAdapter
    private lateinit var memoViewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        calendarViewModel.init()
        return inflater.inflate(com.kai.liu.calendar.R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        monthText.text = calendarViewModel.getMonthName()
        yearText.text = "${calendarViewModel.getYear()}"
        memoText.text= "select a freaking date"

        calendarViewManager = LinearLayoutManager(getActivity())

        calendarViewAdapter = CalendarAdapter(calendarViewModel.createDateList())

        calendarRecyclerView = calendarView.apply {
            setHasFixedSize(false)
            layoutManager = calendarViewManager
            adapter = calendarViewAdapter
        }

        calendarViewModel.getMemo().observe(this, Observer { memo ->
            memo?.let {
                memoViewManager = LinearLayoutManager(getActivity())

                memoViewAdapter = MemoAdapter(memo.list)

                memoRecyclerView = memosView.apply {
                    setHasFixedSize(false)
                    layoutManager = memoViewManager
                    adapter = memoViewAdapter
                }
            }
        })

        prevMonthButton.setOnClickListener({
            monthText.text = calendarViewModel.getPrevMonth()
            calendarViewAdapter.dates = calendarViewModel.createDateList()
            calendarViewAdapter.notifyDataSetChanged()
        })

        nextMonthButton.setOnClickListener({
            monthText.text = calendarViewModel.getNextMonth()
            calendarViewAdapter.dates = calendarViewModel.createDateList()
            calendarViewAdapter.notifyDataSetChanged()
        })

        prevYearButton.setOnClickListener({
            yearText.text = "${calendarViewModel.getPrevYear()}"
            calendarViewAdapter.dates = calendarViewModel.createDateList()
            calendarViewAdapter.notifyDataSetChanged()
        })

        nextYearButton.setOnClickListener({
            yearText.text = "${calendarViewModel.getNextYear()}"
            calendarViewAdapter.dates = calendarViewModel.createDateList()
            calendarViewAdapter.notifyDataSetChanged()
        })

        addMemoButton.setOnClickListener({
            memoMenuView.visibility = View.VISIBLE
            addMemoButton.visibility = View.GONE
        })

        deleteMemoButton.setOnClickListener({

        })

        createMemoButton.setOnClickListener({
            navController.navigate(com.kai.liu.calendar.R.id.action_calendarFragment_to_createMemoFragment)
        })

        closeMemoButton.setOnClickListener({
            memoMenuView.visibility = View.GONE
            addMemoButton.visibility = View.VISIBLE
        })

        drawerMenuButton.setOnClickListener({
            genericDrawerLayout?.let {
                if (genericDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    genericDrawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    genericDrawerLayout.openDrawer(GravityCompat.START)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        calendarViewAdapter.dates = calendarViewModel.createDateList()
        calendarViewAdapter.notifyDataSetChanged()
    }

    inner class CalendarAdapter(var dates: List<List<Int?>>):
            RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

        inner class CalendarViewHolder(val linearLayout: LinearLayout): RecyclerView.ViewHolder(linearLayout)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(com.kai.liu.calendar.R.layout.week_view, parent, false) as LinearLayout

            return CalendarViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dates.size
        }

        override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
            var inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            holder.linearLayout.removeAllViews()

            for (i in 0..6) {
                var subView = inflater.inflate(com.kai.liu.calendar.R.layout.calendar_cell_view, null)
                dates[position][i]?.let { day0 ->
                    val fullDate = "${yearText.text}/${Month.valueOf(monthText.text.toString())}/${day0}"
                    val text = "${day0} "
                    subView.dateText.text =  text.substring(0,2)
                    subView.dateFrame.setOnClickListener({
                        calendarViewModel.setDate(fullDate)
                        calendarViewModel.getMemo(fullDate)
                                .observeForever { memo ->
                                    
                                }
                        notifyDataSetChanged()
                    })

                    calendarViewModel.getMemo(fullDate)
                            .observeForever { memo ->
                                memoText?.let {
                                    if(memo != null) {
                                        if (calendarViewModel.getDate().equals(fullDate)) memoText.text = memo.list.get(0).memo
                                        subView.memoSymbol.visibility = View.VISIBLE
                                    } else {
                                        if (calendarViewModel.getDate().equals(fullDate)) memoText.text = ""
                                        subView.memoSymbol.visibility = View.INVISIBLE
                                    }
                                }
                            }

                    if (calendarViewModel.getDate().equals(fullDate)) {
                        subView.dateText.setBackgroundResource(com.kai.liu.calendar.R.color.dateColor)
                    } else {
                        subView.dateText.setBackgroundResource(com.kai.liu.calendar.R.color.white)
                    }
                } ?: let {
                    subView.dateText.text = "  "
                    subView.dateText.setBackgroundResource(com.kai.liu.calendar.R.color.white)
                }

                var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                params.weight = 1.0f
                subView.layoutParams = params
                holder.linearLayout.addView(subView)
            }
        }
    }

    inner class MemoAdapter(var memos: List<MemoItem>):
            RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

        inner class MemoViewHolder(val linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(com.kai.liu.calendar.R.layout.memo_view, parent, false) as LinearLayout

            return MemoViewHolder(view)
        }

        override fun getItemCount(): Int {
            return memos.size
        }

        override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
            holder.linearLayout.titleMemo.text = memos[position].title
            holder.linearLayout.noteMemo.text = memos[position].memo
        }
    }
}