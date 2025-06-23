using System.ComponentModel.DataAnnotations;

namespace AFORO255.AZ.Web.Models
{
    public class LoginViewModel
    {
        [Display(Name = "UserName")]
        public string? UserName { get; set; }

        [DataType(DataType.Password)]
        [Display(Name = "Password")]
        public string? Password { get; set; }
    }

}
