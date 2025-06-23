using Microsoft.AspNetCore.Identity;

namespace AFORO255.AZ.Web.Extensions
{
    public class CustomClaimTypes : IdentityUser
    {
        public const string Token = "Token";
    }
}
