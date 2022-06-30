//Functions
function render_page(){
    //Grab list_id from page
    let listing_info_span = document.getElementById("listing-info-span")
    let list_id = listing_info_span.innerHTML
    //code to fetch entries and listing obj
    let list_of_entry = get_all_entries_of_listing(list_id)
    let listing_obj = get_listing(list_id)
    
    //render listing info
    render_listing_info(listing_obj)
    //render entry info
    render_entry_info(list_of_entry)
}

function render_listing_info(listing_obj){
    let listing_info_div = document.getElementById("listing-info-div")
    let list_id_p = document.createElement("p")
    list_id_p.setAttribute("id", `list-id-p`)
    list_id_p.innerHTML = `${listing_obj.list_id}`
    listing_info_div.append()
}

function render_entry_info(list_of_entry){

}

function toggle_leave_btn(){
    let leave_btn = document.getElementById("leave-btn")
    if(leave_btn.style.display ==="none")
        leave_btn.style.display = "block";
    else
        leave_btn.style.display = "none";
}


function check_user_id_for_entries(user_id, entry_list){
    //Check if user_id matches any of the entries in entry_list
    //if match call toggle_leave_btn()
}

//Async Functions
async function get_listing(list_id){
    try{
        const response = await fetch(`/listing/manage/${list_id}`)
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain entries! An error occured: ${res.status}`
            throw message
        }
        return pReq
    }catch(err){
        console.log(err)
    }
}

async function get_all_entries_of_listing(list_id){
    try{
        const response = await fetch(`/listing/manage/${list_id}/entry`)
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain entries! An error occured: ${res.status}`
            throw message
        }
        return pReq
    }catch(err){
        console.log(err)
    }
}

async function get_session() {
    try{
        const response = await fetch('/session')
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain requests! An error occured: ${res.status}`
            throw message
        }
        return pReq
    }catch(err){
        console.log(err)
    }
}

async function render_full_page(){
    //Grab list_id from page
    let listing_info_span = document.getElementById("listing-info-span")
    let list_id = listing_info_span.innerHTML
    //code to fetch entries and listing obj
    let listing_obj = get_listing(list_id)
    let list_of_entry = get_all_entries_of_listing(list_id)
    
    listing_info_span.innerHTML = "I got here"
    //get_listing
    try{
        const response1 = await fetch(`/listing/manage/${list_id}`)
        const listing_obj = await response1.json()
        if (response1.status != 200){
            const message = `Couldn't obtain entries! An error occured: ${res.status}`
            throw message
        }
        //get_all_entries_of_listing
        try{
            const response2 = await fetch(`/listing/manage/${list_id}/entry`)
            const list_of_entry = await response2.json()
            if (response.status != 200){
                const message = `Couldn't obtain entries! An error occured: ${res.status}`
                throw message
            }
            //if both requests go through
            let msg = listing_obj + list_of_entry
            listing_info_span.innerHTML = msg
            render_listing_info(listing_obj)
            render_entry_info(list_of_entry)
        }catch(err1){
            console.log(`Error: get_all_entries_of_listing ${err1}`)
        }
    }catch(err){
        console.log(`Error: get_listing ${err}`)
    }
}

toggle_leave_btn()
let session_info = get_session()
//render_full_page()

let listing_info_span = document.getElementById("listing-info-span")
let list_id = listing_info_span.innerHTML
listing_info_span.innerHTML = "testing"