// Fungsi untuk membersihkan form
function clearForm() {
    const form = document.querySelector('.data-form');
    if (form) {
        form.reset();
        
        // Reset semua select elements ke option pertama
        const selects = form.querySelectorAll('select');
        selects.forEach(select => {
            select.selectedIndex = 0;
        });
        
        // Sembunyikan detail perhitungan jika ada
        hideCalculationDetails();
        
        // Show notification
        showNotification('Form telah dibersihkan', 'info');
    }
}

// Fungsi untuk konfirmasi penghapusan
function confirmDelete() {
    return confirm('Apakah Anda yakin ingin menghapus data ini?');
}

// Fungsi untuk menampilkan notifikasi
function showNotification(message, type = 'success') {
    // Hapus notifikasi sebelumnya jika ada
    const existingNotification = document.querySelector('.notification');
    if (existingNotification) {
        existingNotification.remove();
    }
    
    // Buat elemen notifikasi
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    
    // Styling notifikasi
    notification.style.position = 'fixed';
    notification.style.top = '20px';
    notification.style.right = '20px';
    notification.style.padding = '1rem 1.5rem';
    notification.style.borderRadius = '6px';
    notification.style.color = 'white';
    notification.style.zIndex = '1000';
    notification.style.boxShadow = '0 4px 12px rgba(0,0,0,0.15)';
    notification.style.fontWeight = '500';
    notification.style.transition = 'all 0.3s ease';
    
    if (type === 'success') {
        notification.style.backgroundColor = '#28a745';
    } else if (type === 'error') {
        notification.style.backgroundColor = '#dc3545';
    } else if (type === 'info') {
        notification.style.backgroundColor = '#17a2b8';
    } else {
        notification.style.backgroundColor = '#6c757d';
    }
    
    // Tambahkan ke body
    document.body.appendChild(notification);
    
    // Animasi masuk
    setTimeout(() => {
        notification.style.transform = 'translateX(0)';
    }, 10);
    
    // Hapus setelah 4 detik
    setTimeout(() => {
        notification.style.transform = 'translateX(100%)';
        setTimeout(() => {
            if (notification.parentNode) {
                notification.remove();
            }
        }, 300);
    }, 4000);
}

// Fungsi untuk menyembunyikan detail perhitungan
function hideCalculationDetails() {
    const detailsDiv = document.getElementById('calculationDetails');
    if (detailsDiv) {
        detailsDiv.remove();
    }
}

// Fungsi format Rupiah
function formatRupiah(angka) {
    return new Intl.NumberFormat('id-ID').format(angka);
}

// Event listener saat DOM siap
document.addEventListener('DOMContentLoaded', function() {
    // Tambahkan event listener untuk semua form delete
    const deleteForms = document.querySelectorAll('form button[name="delete"]');
    deleteForms.forEach(button => {
        button.addEventListener('click', function(e) {
            if (!confirmDelete()) {
                e.preventDefault();
            }
        });
    });
    
    // Validasi form sebelum submit
    const forms = document.querySelectorAll('.data-form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const requiredFields = form.querySelectorAll('[required]');
            let isValid = true;
            let firstInvalidField = null;
            
            requiredFields.forEach(field => {
                if (!field.value.trim()) {
                    isValid = false;
                    field.style.borderColor = '#dc3545';
                    field.style.boxShadow = '0 0 0 3px rgba(220,53,69,0.1)';
                    
                    if (!firstInvalidField) {
                        firstInvalidField = field;
                    }
                } else {
                    field.style.borderColor = '#e9ecef';
                    field.style.boxShadow = 'none';
                }
            });
            
            if (!isValid) {
                e.preventDefault();
                showNotification('Harap isi semua field yang wajib diisi', 'error');
                
                // Scroll ke field yang error
                if (firstInvalidField) {
                    firstInvalidField.focus();
                }
            }
        });
    });
    
    // Auto-format untuk input harga
    const priceInputs = document.querySelectorAll('input[name="harga"]');
    priceInputs.forEach(input => {
        input.addEventListener('blur', function() {
            if (this.value) {
                // Format ke Rupiah
                const value = parseFloat(this.value).toLocaleString('id-ID');
                this.value = value;
            }
        });
        
        input.addEventListener('focus', function() {
            // Hapus format saat fokus
            this.value = this.value.replace(/[^\d]/g, '');
        });
    });
    
    // Highlight active nav link
    const currentPath = window.location.pathname;
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
    
    // Add loading state to buttons
    const submitButtons = document.querySelectorAll('button[type="submit"]');
    submitButtons.forEach(button => {
        button.addEventListener('click', function() {
            const originalText = this.textContent;
            this.innerHTML = '<i class="loading"></i> Memproses...';
            this.disabled = true;
            
            // Reset setelah 3 detik (fallback)
            setTimeout(() => {
                this.textContent = originalText;
                this.disabled = false;
            }, 3000);
        });
    });
    
    // Auto-focus first input in forms
    const firstInput = document.querySelector('.data-form input:not([type="hidden"])');
    if (firstInput && !firstInput.value) {
        firstInput.focus();
    }
});

// Fungsi untuk menghitung total harga (untuk transaksi)
function hitungTotalHarga() {
    const mediaSelect = document.getElementById('mediaCetak');
    const jumlahInput = document.getElementById('jumlah');
    const pembeliSelect = document.getElementById('pembeli');
    const totalDisplay = document.getElementById('totalHargaDisplay');
    
    if (mediaSelect && mediaSelect.value && jumlahInput && jumlahInput.value && pembeliSelect && pembeliSelect.value) {
        const selectedOption = mediaSelect.options[mediaSelect.selectedIndex];
        const hargaAsli = parseFloat(selectedOption.getAttribute('data-harga'));
        const jumlah = parseInt(jumlahInput.value);
        const isMember = pembeliSelect.options[pembeliSelect.selectedIndex].text.includes('Member');
        
        let hargaSetelahDiskon = hargaAsli;
        
        // Hitung diskon berdasarkan jenis media
        const mediaType = document.getElementById('mediaType').value;
        switch(mediaType) {
            case 'komik':
                hargaSetelahDiskon = hargaAsli * 0.80; // Diskon 20%
                break;
            case 'novel':
                hargaSetelahDiskon = hargaAsli * 0.90; // Diskon 10%
                break;
            case 'majalah':
                hargaSetelahDiskon = hargaAsli * 0.85; // Diskon 15%
                break;
        }
        
        // Diskon tambahan untuk member
        if (isMember) {
            hargaSetelahDiskon *= 0.85; // Diskon tambahan 15% untuk member
        }
        
        const totalHarga = hargaSetelahDiskon * jumlah;
        
        if (totalDisplay) {
            totalDisplay.innerHTML = 'Rp ' + formatRupiah(totalHarga);
        }
        
        // Update hidden field jika ada
        const totalHidden = document.getElementById('totalHarga');
        if (totalHidden) {
            totalHidden.value = totalHarga;
        }
    }
}

console.log('Script loaded successfully!');