# ServiceItem

## Properties
<deflist>
<def title="origin">
	A list of ServiceLocation objects giving original origins of this service.
	Note that a service may have more than one original origin, if the service
	comprises of multiple trains that join at a previous location in the schedule.
	Original Origins will only be available for Arrival and Arrival & Departure
	station boards.
</def>
<def title="destination">
	A list of <a href="">ServiceLocation</a> objects giving original destinations of this
	service. Note that a service may have more than one original destination, if
	the service comprises multiple trains that divide at a subsequent location
	in the schedule. Original Destinations will only be available for Departure
	and Arrival & Departure station boards.
</def>

</deflist>
