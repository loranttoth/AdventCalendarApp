package com.loranttoth.adventcalendar.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.loranttoth.adventcalendar.domain.db.AdventDao
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity
import com.loranttoth.myjobsapp.domain.entity.WindowStateEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val pager: Pager<Int, AdventImageEntity>, private val adventDao: AdventDao) : ViewModel() {

    private val _actImageItem = MutableLiveData<AdventImageEntity>()
    val actImageItem: MutableLiveData<AdventImageEntity>
        get() = _actImageItem

    private val _isWindowOpened = MutableLiveData<Boolean>()
    val isWindowOpened: MutableLiveData<Boolean>
        get() = _isWindowOpened

    val adventPagingFlow = pager.flow.map { pagingData ->
        pagingData.map { it }
    }.cachedIn(viewModelScope)

    private val _openedList = MutableStateFlow<List<WindowStateEntity>>(emptyList())
    val openedList = _openedList.asStateFlow()
    init {
        viewModelScope.launch (Dispatchers.IO) {
            adventDao.getWindowState().distinctUntilChanged()
                .collect { listOfOpenedWindows ->
                    if (listOfOpenedWindows.isNullOrEmpty()) {
                        _openedList.value = emptyList()
                    } else {
                        _openedList.value = listOfOpenedWindows
                    }
                }
        }
    }

    fun openWindow(adventImageEntity: AdventImageEntity) = viewModelScope.launch { adventDao.insertWindowState(WindowStateEntity(adventImageEntity.position)) }

    fun isOpenable(day: Int): Boolean {
        val caldec1 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, 11)
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
        }
        val cal = Calendar.getInstance()

        return (cal.after(caldec1) && ((cal.get(Calendar.YEAR) > 2023 || cal.get(Calendar.DATE) >= day)))
    }

    fun getDateString(): String {
        val cal = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("YYYY.MM.dd")
        val date = java.util.Date(cal.timeInMillis)
        return dateFormat.format(date)
    }
}

