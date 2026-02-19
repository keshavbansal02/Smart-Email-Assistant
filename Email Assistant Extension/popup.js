document.getElementById('saveBtn').addEventListener('click', () => {
    const tone = document.getElementById('tone').value;
    chrome.storage.local.set({ selectedTone: tone }, () => {
        alert("Tone saved: " + tone);
    });
});