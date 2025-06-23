using System.Security.Claims;
using System.Security.Principal;

namespace AFORO255.AZ.Web.Extensions
{
    public static class IdentityExtensions
    {
        public static string GetToken(this IIdentity identity)
        {
            ClaimsIdentity claimsIdentity = identity as ClaimsIdentity;
            Claim claim = claimsIdentity?.FindFirst("Token");

            if (claim == null)
                return "";

            return claim.Value;
        }
    }
}
