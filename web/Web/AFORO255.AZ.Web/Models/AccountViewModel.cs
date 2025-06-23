using System.ComponentModel.DataAnnotations;

namespace AFORO255.AZ.Web.Models
{
    public class AccountViewModel
    {
        [Display(Name = "IdAccount")]
        public int IdAccount { get; set; }

        [Display(Name = "Type")]
        public string? Type { get; set; }

        [Display(Name = "Amount")]
        public decimal Amount { get; set; }
    }
}

