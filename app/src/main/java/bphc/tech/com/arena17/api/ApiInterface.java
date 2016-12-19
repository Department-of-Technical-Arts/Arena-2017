package bphc.tech.com.arena17.api;

import bphc.tech.com.arena17.sets.EventsData;
import bphc.tech.com.arena17.sets.ScheduleData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tejeshwar on 19/12/16.
 */

public interface ApiInterface {

    @GET()
    Call<ScheduleData> getSchedule(@Query("tag") String tag);

    @GET()
    Call<EventsData> getEvents(@Query("tag") String tag);
}
