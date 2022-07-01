//Functions
function test_update_span(session){
    let element = document.getElementById("span-1")
    element.innerHTML = `user_id: ${session.user_id} is_mod: ${session.is_mod} is_frozen: ${session.is_frozen} error: ${session.error}`
}

function create_listing_div(){
    let listing_div = document.createElement("div")
    listing_div.setAttribute("id","list_of_listings")
    listing_div.setAttribute("class", "row mb-3")
    return listing_div
}

function create_row_div(){
    let row_div = document.createElement("div")
    row_div.setAttribute("class", "row mb-3 listing-row-div")
    return row_div
}

function create_list_id_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "themed-grid-col")
    element.style.display = "none"
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "list_id")
    p_element.setAttribute("id", `list-id-${listing.list_id}`)
    p_element.innerHTML = `${listing.list_id}`
    element.append(p_element)

    return element
}

function create_user_id_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "user_id")
    p_element.setAttribute("id", `user-id-${listing.user_id}`)
    p_element.innerHTML = `${listing.user_id}`
    element.append(p_element)

    return element
}
function create_list_name_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-3 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "list_name")
    p_element.setAttribute("id", `list-name-${listing.list_name}`)
    p_element.innerHTML = `${listing.list_name}`
    element.append(p_element)

    return element
}
function create_dungeonName_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-4 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "dungeonName")
    p_element.setAttribute("id", `dungeonName-${listing.dungeonName}`)
    p_element.innerHTML = `${listing.dungeonName}`
    element.append(p_element)

    return element
}
function create_max_size_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "max_size")
    p_element.setAttribute("id", `max-size-${listing.max_size}`)
    p_element.innerHTML = `${listing.max_size}`
    element.append(p_element)

    return element
}
function create_cur_size_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "cur_size")
    p_element.setAttribute("id", `cur-size-${listing.cur_size}`)
    p_element.innerHTML = `${listing.cur_size}`
    element.append(p_element)

    return element
}
function create_view_listing_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-2 themed-grid-col mt-3 auto")
    element.append(create_button(listing))
    return element
}
function create_button(listing){
    let button = document.createElement("input")
    button.setAttribute("id", `select-listing-${listing.list_id}`)
    button.setAttribute("type", "button")
    button.setAttribute("name", `${listing.list_id}`)
    button.setAttribute("class", "btn btn-secondary view-listing-btn")
    button.setAttribute("value", "Select Listing")
    button.addEventListener("click", update_list_id)
    return button
}

function remove_listings(){
    let div_location = document.getElementById("list_of_listings")
    div_location.remove()
}

function render_listings(all_listings){
    //Get location to append to
    let div_location = document.getElementById("listing-display-div")
    //Create new div to append to this location
    let listing_div = create_listing_div()
    //Create div for each row (listing)
    for (let listing_json of all_listings.my_list){
        let row_div = create_row_div(listing_json)
        listing_div.append(row_div)
        const list_of_elements = []
        //Create element(s) for containing listing information
        list_of_elements.push(create_list_id_element(listing_json))
        list_of_elements.push(create_user_id_element(listing_json))
        list_of_elements.push(create_list_name_element(listing_json))
        list_of_elements.push(create_dungeonName_element(listing_json))
        list_of_elements.push(create_max_size_element(listing_json))
        list_of_elements.push(create_cur_size_element(listing_json))
        //Create view_listing_btn
        list_of_elements.push(create_view_listing_element(listing_json))
        //Append everything to new row div
        for(element of list_of_elements){
            row_div.append(element)
        }
    }
    //Append new div to location
    div_location.append(listing_div);
}

function setup_btns(){
    let div_location = document.getElementById("filter-btn-container")
    let button = document.createElement("input")
    //let fitler_button = document.get
    button.setAttribute("type", "button")
    button.setAttribute("name", "filter-collapse")
    button.setAttribute("class", "btn btn-secondary filter-btn")
    button.setAttribute("value", "Filter Listings")
    button.addEventListener("click", hideCollapse)
    div_location.append(button)

    let div_location2 = document.getElementById("create-btn-container")
    let button2 = document.createElement("input")
    button2.setAttribute("type", "button")
    button2.setAttribute("name", "create-collapse")
    button2.setAttribute("class", "btn btn-secondary create-btn")
    button2.setAttribute("value", "Create Listing")
    button2.addEventListener("click", hideCollapse)
    div_location2.append(button2)
}

function hideCollapse() {
    var myCollapse = document.getElementById(this.getAttribute("name"));
    var bsCollapse = new bootstrap.Collapse(myCollapse, {
        toggle: true
    })
    bsCollapse.hide();
}

function update_list_id(){
    let element_list_id = this.getAttribute("name")
    let element_form = document.getElementById("list_id")
    element_form.setAttribute("value", element_list_id)
    let view_btn = document.getElementById("view-btn")
    view_btn.setAttribute("class", "btn btn-success")
    console.log(element_form.getAttribute("value"))
}

function clear_filters(){
    remove_listings()
    get_all_listings()
}
async function filter_listing() {
    remove_listings();
    await get_all_listings()

    let categories = document.getElementById('filter-category')
    let category = categories.options[categories.selectedIndex].value
    let filtered_elements = document.getElementsByClassName(category)
    let specific_filter_val = document.getElementById("specific-filter-input").value
    const filtered_html = [];
    for(listing of filtered_elements){
        
        
        if(listing.innerText == specific_filter_val){
            console.log(listing);
            let cat_element = listing.parentElement
            let listing_row_html = cat_element.parentElement
            filtered_html.push(listing_row_html)
        }
    }
    let filtered_json = html_to_json(filtered_html)
    
    remove_listings();
    render_listings(filtered_json);
    
}

function html_to_json(html_list){
    let json_string = "";
    json_string+="{\"my_list\":[";
    for(let html of html_list){
        json_string+=`{\"list_id\": ${html.children[0].innerText},`;
        json_string+=`\"user_id\": ${html.children[1].innerText},`;
        json_string+=`\"list_name\": "${html.children[2].innerText}",`;
        json_string+=`\"dungeonName\": "${html.children[3].innerText}",`;
        json_string+=`\"max_size\": ${html.children[4].innerText},`;
        json_string+=`\"cur_size\": ${html.children[5].innerText}}`;
        if(html_list.indexOf(html)<html_list.length-1){
            json_string+=",";
        }
    }
    json_string+="]}";
    let json_obj = JSON.parse(json_string);
    //console.log(json_string);
    //console.log("json parsed" + json_obj);
    console.log(json_obj);
    return json_obj;
}

//user_id
//list_name
//dungeonName
//max_size

//Async functions
async function get_session() {
    try{
        const response = await fetch('/session')
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain requests! An error occured: ${res.status}`
            throw message
        }
        test_update_span(pReq)
    }catch(err){
        console.log(err)
    }
}

async function get_all_listings(){
    try{
        const response = await fetch('/listing/all-listings')
        const results = await response.json()
        if(response.status =! 200){
            const message = `An error occured: Couldn't obtain listings  ${res.status}`
            throw message
        }
        render_listings(results)
    }catch(e){
        console.log(e)
    }
}


console.log("this is working")

get_session()
get_all_listings()
setup_btns()


/*


//let listings = document.getElementById("list_of_listings").children[0].children[1].children[0].innerHTML; - return user_id



/**
 * 
const response = await fetch('/account/cancel', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({
            'req_id': reqId
        })
    })

let formData = new FormData()
formData.append('list_id', list_id)
const response = await fetch("/listing", {
    method: 'POST',
    body: formData
})
if(!response.ok){
    console.log("Could not load listing.vm from javascript")
}
 */
