console.log("Email Assistant with Tone Selection Loaded!");

function createAssistantUI() {
    
    const container = document.createElement('div');
    container.id = 'ai-assistant-container';
    container.style.cssText = "display:inline-flex; align-items:center; margin-right:8px; background:#f1f3f4; border-radius:4px; padding:2px;";

    const toneSelect = document.createElement('select');
    toneSelect.id = 'Reply-Tone';
    toneSelect.style.cssText = "border:none; background:transparent; font-size:12px; padding:5px; cursor:pointer; outline:none; border-right:1px solid #ccc; margin-right:5px;";
    
    const tones = ["Professional","Friendly", "Casual", "Concise","Hinglish","Romantic"];
    tones.forEach(tone => {
        const option = document.createElement('option');
        option.value = tone;
        option.innerText = tone;
        toneSelect.appendChild(option);
    });


    const btn = document.createElement('div');
    btn.innerHTML = "Smart-Email-Assistant";
    btn.style.cssText = "background:#4285f4; color:white; padding:0 12px; border-radius:0 4px 4px 0; cursor:pointer; font-size:13px; font-weight:bold; height:30px; display:inline-flex; align-items:center;";


    const style = document.createElement('style');
    style.innerHTML = `
        @keyframes spin { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }
        .loader { border: 2px solid #f3f3f3; border-top: 2px solid #ffffff; border-radius: 50%; width: 12px; height: 12px; animation: spin 0.8s linear infinite; margin-right: 5px; }
    `;
    document.head.appendChild(style);

    btn.onclick = async () => {
        const originalText = btn.innerHTML;
        const selectedTone = toneSelect.value; 
        
        btn.innerHTML = '<div class="loader"></div>...';
        btn.style.pointerEvents = "none";

        const emailBody = document.querySelector('.h7')?.innerText || "No content found";

        try {
            const response = await fetch('http://localhost:8080/email/generate-email', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    emailContent: emailBody,
                    emailTone: selectedTone 
                })
            });

            const data = await response.text();
            const cleanData = data.replace("Email Generated : ", "");
            const replyBox = document.querySelector('[role="textbox"]');
            
            if (replyBox) {
                replyBox.innerText = cleanData;
            }
        } catch (error) {
            alert("kindly check the Backend!");
        } finally {
            btn.innerHTML = originalText;
            btn.style.pointerEvents = "auto";
        }
    };

    container.appendChild(toneSelect);
    container.appendChild(btn);
    return container;
}

const observer = new MutationObserver(() => {
    const toolbar = document.querySelector('.btC');
    if (toolbar && !document.getElementById('ai-assistant-container')) {
        toolbar.insertBefore(createAssistantUI(), toolbar.firstChild);
    }
});

observer.observe(document.body, { childList: true, subtree: true });