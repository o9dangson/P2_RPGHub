//Functions
function render_all_entries(){

}

function toggle_leave_btn(){
    let leave_btn = document.getElementById("leave-btn")
    if(leave_btn.style.display ==="none")
        leave_btn.style.display = "block";
    else
        leave_btn.style.display = "none";
}

function render_page(){
    //code to fetch all entries for this listing
    let list_of_entry = get_list_of_entry()
    //code to fetch listing itself
    let listing_json =   
    
    //render listing info
    render_listing_info(listing_json)
    //render entry info
    render_entry_info(list_of_entry)
}


function check_user_id_for_entries(user_id, entry_list){
    //Check if user_id matches any of the entries in entry_list
    //if match call toggle_leave_btn()
}

//Async Functions

async function get_all_entries_of_listing(list_id){
    try{
        const response = await fetch(`/listing/manage/entry/${list_id}`)
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

toggle_leave_btn()
let session_info = get_session()
console.log(session_info.user_id)